#!flask/bin/python
#!/usr/bin/env python
# -*- coding: utf-8 -*-
from flask import Flask, jsonify
import urllib, json

class Lane:

    def __init__(self):
        self.laneList = list()

        url = "https://datosabiertos.malaga.eu/recursos/transporte/trafico/da_carrilesBici-25830.geojson"
        response = urllib.urlopen(url)
        self.data = response.read()
        features = json.loads(self.data)['features']

        for feature in features:
            id = feature['id']
            ogc_fid = feature['properties']['ogc_fid']
            name = feature['properties']['name']
            description = feature['properties']['description']
            coordinates = list()
            geometry = feature['geometry']['coordinates']
            #print geometry
            for coordinate in geometry:
                coordinates.append(coordinate)
            self.laneList.append({'name':name,'id':id,'ogc_fid':ogc_fid,'description':description,'coordinates':coordinates})

    def get_lanes(self):
        return jsonify(self.laneList)

    def count(self):
        return jsonify({'count':len(self.laneList)})

    def get_list_names(self):
        namesList = list()
        for lane in self.laneList:
            namesList.append({'id':lane['id'],'name':lane['name']})
        return jsonify(namesList)

    def get_list_id_names(self):
        namesList = list()
        for lane in self.laneList:
            namesList.append({'ogc_fid':lane['ogc_fid'],'name':lane['name']})
        return jsonify(namesList)

    def find_by_id(self, id):
        index = 0
        while index < len(self.laneList):
            if self.laneList[index]['id'] == id:
                return jsonify(self.laneList[index])
            index +=1
        return jsonify({'id':'-1','name':'Carril no existente','description':' ','ogc_fid':-1})

    def find_by_ogc_fid(self, id):
        index = 0
        while index < len(self.laneList):
            if self.laneList[index]['ogc_fid'] == id:
                return jsonify(self.laneList[index])
            index +=1
        return jsonify({'id':'-1','name':'Carril no existente','description':' ','ogc_fid':-1})
