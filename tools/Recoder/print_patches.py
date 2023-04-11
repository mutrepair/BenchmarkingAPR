import os, sys
from os.path import *
import json

print('usage: python print_patches.py output_dir patches_root_dir')

output_dir = sys.argv[1] # 'output_d4j_ori'
patches_root_dir = sys.argv[2] # 'method_patches_d4j_ori'
for fix in os.listdir(output_dir):
    if not fix.endswith('.fix'): continue
    bug_id = fix.split('.')[0]
    patch_bug_dir = join(patches_root_dir, bug_id)
    os.makedirs(patch_bug_dir, exist_ok=True)
    if not isdir(patch_bug_dir):
        os.makedirs(patch_bug_dir)
    with open(join(output_dir, fix)) as f:
        patches = json.load(f)
    for patch_id in patches.keys():
        with open(join(patches_root_dir, bug_id, patch_id + '.java'), 'w') as f:
            f.write(patches[patch_id])