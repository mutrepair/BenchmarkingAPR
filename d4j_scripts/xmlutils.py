import xml.etree.ElementTree as ET
from os.path import *

pom_prefix = '{http://maven.apache.org/POM/4.0.0}'
ET.register_namespace('', pom_prefix[1:-1])

def get_parent_node(root:ET.Element, node:ET.Element):
    parent_map = {child: parent for parent in root.iter() for child in parent}
    return parent_map[node]

def delete_node(src_doc_path:str, tgt_name:str, filter, tgt_doc_path:str=None):
    assert isfile(src_doc_path), 'src_doc_path not found: ' + src_doc_path
    if tgt_doc_path is None:
        tgt_doc_path = src_doc_path
    tree = ET.parse(src_doc_path)
    root = tree.getroot()
    tgt_node_list = []
    prefix = './/' + pom_prefix
    for target in root.findall(prefix + tgt_name):
        if filter(root, target):
            tgt_node_list.append(target)
    assert len(tgt_node_list) <= 1, 'more than one tgt_node found'
    if len(tgt_node_list) == 1:
        tgt_node = tgt_node_list[0]
        parent_node = get_parent_node(root, tgt_node)
        parent_node.remove(tgt_node)
    else:
        # no target node found
        parent_node = None
    return parent_node, tree

def add_node_for_target(src_doc_path:str, tgt_name:str, filter, new_node_name:str, new_node_text:str, tgt_doc_path:str=None):
    assert isfile(src_doc_path), 'src_doc_path not found: ' + src_doc_path
    if tgt_doc_path is None:
        tgt_doc_path = src_doc_path
    tree = ET.parse(src_doc_path)
    root = tree.getroot()
    tgt_node_list = []
    prefix = './/' + pom_prefix
    for target in root.findall(prefix + tgt_name):
        if filter(root, target):
            tgt_node_list.append(target)
    assert len(tgt_node_list) == 1, 'tgt_node not found or more than one found: ' + str(len(tgt_node_list))
    tgt_node = tgt_node_list[0]
    new_node = ET.SubElement(tgt_node, new_node_name)
    new_node.text = new_node_text
    
    tree.write(tgt_doc_path)
    
def update_node_for_target(src_doc_path:str, tgt_name:str, filter, new_node_text:str, tgt_doc_path:str=None):
    # return if not found
    assert isfile(src_doc_path), 'src_doc_path not found: ' + src_doc_path
    if tgt_doc_path is None:
        tgt_doc_path = src_doc_path
    tree = ET.parse(src_doc_path)
    root = tree.getroot()
    tgt_node_list = []
    prefix = './/' + pom_prefix
    for target in root.findall(prefix + tgt_name):
        if filter(root, target):
            tgt_node_list.append(target)
    assert len(tgt_node_list) <= 1
    if len(tgt_node_list) == 0:
        return
    tgt_node = tgt_node_list[0]
    tgt_node.text = new_node_text
    
    tree.write(tgt_doc_path)

def add_node_list_for_target(src_doc_path:str, tgt_name:str, filter, group_name:str, new_node_text_list:list, tgt_doc_path:str=None):
    assert isfile(src_doc_path), 'src_doc_path not found: ' + src_doc_path
    if tgt_doc_path is None:
        tgt_doc_path = src_doc_path
    tree = ET.parse(src_doc_path)
    root = tree.getroot()
    tgt_node = []
    prefix = './/' + pom_prefix
    for target in root.findall(prefix + tgt_name):
        if filter(root, target):
            tgt_node.append(target)
    assert len(tgt_node) == 1, 'tgt_node not found or more than one found'
    tgt_node = tgt_node[0]
    group_node = ET.SubElement(tgt_node, group_name)
    for item in new_node_text_list:
        new_node = ET.SubElement(group_node, 'param')
        new_node.text = item
        
    tree.write(tgt_doc_path)