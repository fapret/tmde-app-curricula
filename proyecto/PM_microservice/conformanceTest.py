'''
Created on 13 ago. 2025

@author: sndc3
'''
import pm4py
import os
import json
import uuid
import pandas as pd
from flask import Flask, request, jsonify, send_file
from flask_cors import CORS

import traceback #for debugging

app = Flask(__name__)
CORS(app)

# Create required folders if doesnt exists
for folder in ['./conformance']:
    os.makedirs(folder, exist_ok=True)

@app.route('/<mode>/<reference>/<caseid>', methods=['GET'])
def TBR(mode, reference, caseid):
    try:
        pnfilepath = './pnml/' + reference + '_' + mode + '.pnml'
        elfilepath = './imports2/' + caseid + '.xes'
        
        if not os.path.exists(pnfilepath):
            return "Unknown reference id"
        if not os.path.exists(elfilepath):
            return "Unknown discovery id"
        
        filepath = './conformance/' + reference + '/' + caseid + '_' + mode + '.json'
        
        if os.path.exists(filepath):
            with open(filepath, "r", encoding="utf-8") as f:
                data = json.load(f)
            return jsonify(data)
        
        event_log = pm4py.read_xes(elfilepath)
        net, im, fm = pm4py.read_pnml(pnfilepath)
        
        conformance_diagnostics = pm4py.conformance.conformance_diagnostics_token_based_replay(event_log, net, im, fm)
        
        conformance_fitness = pm4py.fitness_token_based_replay(event_log, net, im, fm)
        
        conformance_precision = pm4py.precision_token_based_replay(event_log, net, im, fm)        

        result = {
            "token_based_replay": conformance_diagnostics,
            "fitness": conformance_fitness,
            "precision": conformance_precision
        }
        
        with open(filepath, "w", encoding="utf-8") as f:
            json.dump(result, f, indent=4, ensure_ascii=False)
        
        return jsonify(result)
    
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9007)