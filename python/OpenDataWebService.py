#!flask/bin/python
#!/usr/bin/env python
# -*- coding: utf-8 -*-
from flask import Flask, jsonify
import urllib, json
from Lane import Lane

# http://localhost:5000

app = Flask(__name__)

# URL = "https://datosabiertos.malaga.eu/recursos/transporte/trafico/da_carrilesBici-25830.geojson"

# Consultas sin parametros

@app.route('/opendata/api/bicilane', methods=['GET']) #url http://localhost:5000/opendata/api/bicilane
def get_lanes():
    laneList = Lane()
    return laneList.get_lanes()

@app.route('/opendata/api/bicilane/count', methods=['GET']) #url http://localhost:5000/opendata/api/bicilane/count
def get_count():
    laneList = Lane()
    return laneList.count()

@app.route('/opendata/api/bicilane/list_names', methods=['GET']) #url http://localhost:5000/opendata/api/bicilane/list_names
def get_list_names():
    laneList = Lane()
    return laneList.get_list_names()

@app.route('/opendata/api/bicilane/list_id_names', methods=['GET']) #url http://localhost:5000/opendata/api/bicilane/list_id_names
def get_list_id_names():
    laneList = Lane()
    return laneList.get_list_id_names()


# Consultas parametrizadas

#ejemplo de id: da_carrilesBici.fid--5673ced1_16ed9249c70_-4eb
@app.route('/opendata/api/bicilane/find_by_id/<string:id>', methods=['GET']) #url http://localhost:5000/opendata/api/bicilane/find_by_id/da_carrilesBici.fid--5673ced1_16ed9249c70_-4eb
def find_by_id(id):
    laneList = Lane()
    return laneList.find_by_id(id)

@app.route('/opendata/api/bicilane/find_by_ogc_fid/<int:id>', methods=['GET']) #url http://localhost:5000/opendata/api/bicilane/find_by_ogc_fid/43279
def find_by_ogc_fid(id):
    laneList = Lane()
    return laneList.find_by_ogc_fid(id)

if __name__ == '__main__':
    app.run(debug=True)
