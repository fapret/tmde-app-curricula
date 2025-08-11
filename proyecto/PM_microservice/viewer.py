'''
Created on 10 jul. 2025

@author: Santiago Nicolas Diaz Conde
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

# Create required folders if doesnt exists
for folder in ['./imports', './dfg/png', './bpmn/png', './pnml/png', './ptml/png']:
    os.makedirs(folder, exist_ok=True)

@app.route('/<view>/<caseid>', methods=['GET'])
def viewer(view, caseid):
    try:
        match view:
            case 'dfg':
                filepath = './dfg/png/' + caseid + '.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
                dfg, dfg_start_activities, dfg_end_activities = pm4py.read_dfg('./dfg/' + caseid + '.dfg')
                pm4py.save_vis_dfg(dfg, dfg_start_activities, dfg_end_activities, filepath, 'white', 9223372036854775807, 'TB')
                return send_file(filepath, mimetype='image/png')
            
            case 'perf_dfg':
                filepath = './dfg/png/' + caseid + '_performance.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
                filepathLog = './imports/' + caseid + '.xes'
                if os.path.exists(filepathLog):
                    event_log = pm4py.read_xes(filepathLog)
                    event_log = pm4py.format_dataframe(event_log, case_id="ID", activity_key="Activity", timestamp_key="Timestamp", timest_format='%a %b %d %H:%M:%S %Z %Y')
                    event_log = pm4py.convert_to_event_log(event_log)
                    dfg, dfg_start_activities, dfg_end_activities = pm4py.discover_performance_dfg(event_log);
                    pm4py.save_vis_performance_dfg(dfg, dfg_start_activities, dfg_end_activities, filepath, rankdir='TB')
                    return send_file(filepath, mimetype='image/png')
                
            case 'bpmn':
                filepath = './bpmn/png/' + caseid + '.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
                bpmn_graph = pm4py.read_bpmn('./bpmn/' + caseid + '.bpmn')
                pm4py.save_vis_bpmn(bpmn_graph, filepath, 'white', 'TB')
                return send_file(filepath, mimetype='image/png')
            
            case 'pnml_alpha':
                filepath = './pnml/png/' + caseid + '_alpha.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                net, im, fm = pm4py.read_pnml('./pnml/' + caseid + '_alpha.pnml')
                pm4py.save_vis_petri_net(net, im, fm, filepath, rankdir='TB')
                return send_file(filepath, mimetype='image/png')
                
            case 'pnml_heuristics':
                filepath = './pnml/png/' + caseid + '_heuristics.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                net, im, fm = pm4py.read_pnml('./pnml/' + caseid + '_heuristics.pnml')
                pm4py.save_vis_petri_net(net, im, fm, filepath, rankdir='TB')
                return send_file(filepath, mimetype='image/png')
                
            case 'ptml':
                filepath = './ptml/png/' + caseid + '.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                process_tree = pm4py.read_ptml('./ptml/' + caseid + '.ptml')
                pm4py.save_vis_process_tree(process_tree, filepath)
                return send_file(filepath, mimetype='image/png')
            
            case _:
                return "Unknown view"
    
    except Exception as e:
        return jsonify({'error': str(e)}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9001)