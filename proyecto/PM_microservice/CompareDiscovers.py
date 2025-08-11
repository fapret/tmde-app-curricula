'''
Created on 02 ago. 2025

@author: sndc3
'''
import pm4py
import os
import uuid
import pandas as pd
from flask import Flask, request, jsonify, send_file
from flask_cors import CORS

import traceback #for debugging

app = Flask(__name__)
CORS(app) #Without cors it gets blocked

def normalize_perf(perf_dict):
    # perf_dict: dict with values as dicts containing median, etc.
    result = {}
    for k, v in perf_dict.items():
        # v is a dict; we want median duration
        median_ms = v.get('median', None)
        if median_ms is not None:
            result[k] = median_ms / (1000 * 60 * 60 * 24 * 30.44)  # convert ms to months
        else:
            result[k] = None
    return result


@app.route('/<discoverA>/<discoverB>', defaults={'completeOnly': 'false'}, methods=['GET'])
@app.route('/<discoverA>/<discoverB>/<completeOnly>', methods=['GET'])
def compareDiscover(discoverA, discoverB, completeOnly):
    try:
        filepathA = './imports2/' + discoverA + '.xes'
        filepathB = './imports2/' + discoverB + '.xes'
        
        if os.path.exists(filepathA) and os.path.exists(filepathB):
            
            event_logA = pm4py.read_xes(filepathA)
            event_logA = pm4py.format_dataframe(event_logA, case_id="ID", activity_key="Activity", timestamp_key="Timestamp", timest_format='%a %b %d %H:%M:%S %Z %Y')
            
            event_logB = pm4py.read_xes(filepathB)
            event_logB = pm4py.format_dataframe(event_logB, case_id="ID", activity_key="Activity", timestamp_key="Timestamp", timest_format='%a %b %d %H:%M:%S %Z %Y')
            
            # Filter only traces that contain "Degree obtained"
            if completeOnly.lower() == "true":
                ids_with_degree_A = event_logA.loc[event_logA["Activity"] == "Degree obtained", "ID"].unique()
                event_logA = event_logA[event_logA["ID"].isin(ids_with_degree_A)]
                
                ids_with_degree_B = event_logB.loc[event_logB["Activity"] == "Degree obtained", "ID"].unique()
                event_logB = event_logB[event_logB["ID"].isin(ids_with_degree_B)]
            
            event_logA = pm4py.convert_to_event_log(event_logA)
            event_logB = pm4py.convert_to_event_log(event_logB)
            
            performance_dfgA, start_activitiesA, end_activitiesA = pm4py.discover_performance_dfg(event_logA)
            performance_dfgB, start_activitiesB, end_activitiesB = pm4py.discover_performance_dfg(event_logB)
            
            performance_dfgA = normalize_perf(performance_dfgA)
            performance_dfgB = normalize_perf(performance_dfgB)
            
            rows = []
            for transition in set(performance_dfgA.keys()).union(performance_dfgB.keys()):
                real = performance_dfgA.get(transition, None)
                ref = performance_dfgB.get(transition, None)
                deviation = None
                if real is not None and ref is not None:
                    deviation = real - ref
                rows.append({
                    "From": transition[0],
                    "To": transition[1],
                    "DisA_Months": real,
                    "DisB_Months": ref,
                    "Deviation_Months": deviation
                })
            
            df_comparison = pd.DataFrame(rows)
            df_comparison = df_comparison.sort_values("Deviation_Months", ascending=False)
            
            return jsonify(df_comparison.to_dict(orient='records'))
            
        else:
            return "Unknown discover id"
        
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9005)