'''
Created on 10 aug. 2025

@author: Santiago Nicolas Diaz Conde
'''
import pm4py
import os
import uuid
import pandas as pd
import threading
from flask import Flask, request, jsonify, send_file
from flask_cors import CORS

import traceback #for debugging

app = Flask(__name__)
CORS(app) #Without cors it gets blocked

# Create required folders if doesnt exists
for folder in ['./imports', './dfg/png', './bpmn/png', './pnml/png', './ptml/png']:
    os.makedirs(folder, exist_ok=True)
    
render_status = {}

def run_viewer(view, caseid, mode):
    try:
        render_status[(view, caseid)] = "rendering"
        match view:
            case 'dfg':
                filepath = './dfg/png/' + caseid + '.png'
                
                dfg, dfg_start_activities, dfg_end_activities = pm4py.read_dfg('./dfg/' + caseid + '.dfg')
                pm4py.save_vis_dfg(dfg, dfg_start_activities, dfg_end_activities, filepath, 'white', 9223372036854775807, 'TB', engine="neato")
                #return send_file(filepath, mimetype='image/png')
            
            case 'perf_dfg':
                filepath = './dfg/png/' + caseid + '_performance.png'
                
                if mode == 1:
                    filepathLog = './reference/' + caseid + '.xes'
                else:
                    filepathLog = './imports2/' + caseid + '.xes'
                if os.path.exists(filepathLog):
                    event_log = pm4py.read_xes(filepathLog)
                    event_log = pm4py.format_dataframe(event_log, case_id="ID", activity_key="Activity", timestamp_key="Timestamp", timest_format='%a %b %d %H:%M:%S %Z %Y')
                    event_log = pm4py.convert_to_event_log(event_log)
                    dfg, dfg_start_activities, dfg_end_activities = pm4py.discover_performance_dfg(event_log);
                    pm4py.save_vis_performance_dfg(dfg, dfg_start_activities, dfg_end_activities, filepath, rankdir='TB', engine="neato")
                    #return send_file(filepath, mimetype='image/png')
                
            case 'bpmn':
                filepath = './bpmn/png/' + caseid + '.png'
                
                bpmn_graph = pm4py.read_bpmn('./bpmn/' + caseid + '.bpmn')
                pm4py.save_vis_bpmn(bpmn_graph, filepath, 'white', 'TB', engine="neato")
                #return send_file(filepath, mimetype='image/png')
            
            case 'pnml_alpha':
                filepath = './pnml/png/' + caseid + '_alpha.png'

                net, im, fm = pm4py.read_pnml('./pnml/' + caseid + '_alpha.pnml')
                pm4py.save_vis_petri_net(net, im, fm, filepath, rankdir='TB', engine="neato")
                #return send_file(filepath, mimetype='image/png')
                
            case 'pnml_heuristics':
                filepath = './pnml/png/' + caseid + '_heuristics.png'

                net, im, fm = pm4py.read_pnml('./pnml/' + caseid + '_heuristics.pnml')
                pm4py.save_vis_petri_net(net, im, fm, filepath, rankdir='TB', engine="neato")
                #return send_file(filepath, mimetype='image/png')
                
            case 'pnml_inductive':
                filepath = './pnml/png/' + caseid + '_inductive.png'

                net, im, fm = pm4py.read_pnml('./pnml/' + caseid + '_inductive.pnml')
                pm4py.save_vis_petri_net(net, im, fm, filepath, rankdir='TB', engine="neato")
                #return send_file(filepath, mimetype='image/png')   
                
            case 'ptml':
                filepath = './ptml/png/' + caseid + '.png'
                process_tree = pm4py.read_ptml('./ptml/' + caseid + '.ptml')
                pm4py.save_vis_process_tree(process_tree, filepath, engine="neato")
                #return send_file(filepath, mimetype='image/png')
            
            case _:
                return "Unknown view"
            
        render_status[(view, caseid)] = "completed"
    
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
@app.route('/<view>/<caseid>/<mode>', methods=['GET'])
@app.route('/<view>/<caseid>', methods=['GET'])
def viewer(view, caseid, mode):
    try:
        # If rendering is ongoing, report status
        if render_status.get((view, caseid)) == "rendering":
            return jsonify({'status': 'rendering'}), 202
        match view:
            case 'dfg':
                filepath = './dfg/png/' + caseid + '.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
            case 'perf_dfg':
                filepath = './dfg/png/' + caseid + '_performance.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
    
            case 'bpmn':
                filepath = './bpmn/png/' + caseid + '.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
    
            case 'pnml_alpha':
                filepath = './pnml/png/' + caseid + '_alpha.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
            case 'pnml_heuristics':
                filepath = './pnml/png/' + caseid + '_heuristics.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
            case 'pnml_inductive':
                filepath = './pnml/png/' + caseid + '_inductive.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
            case 'ptml':
                filepath = './ptml/png/' + caseid + '.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
            case _:
                return "Unknown view"
            
        thread = threading.Thread(target=run_viewer, args=(view, caseid, mode))
        thread.start()
        return jsonify({'status': 'rendering started'}), 200
                    
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9001)