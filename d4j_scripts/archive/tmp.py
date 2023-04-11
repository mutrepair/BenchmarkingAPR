import os
from os.path import *
from defects4j import *
from utils import *

proj_root_dir = '/home/jun/fastd/dlapr-mirror/validation/tmp_repo'
for proj in all_projects:
    d4j_proj = Defects4J(proj, '25' if proj == 'collections' else '13' if proj == 'mockito' else '1', 'fixed', join(proj_root_dir, proj))
    d4j_proj.checkout_if_not_exist_or_empty()
    d4j_proj.test()
    all_tests = file_to_ids(join(d4j_proj.proj_home, 'all_tests'))
    assert len(all_tests) > 0
    print(proj, len(all_tests))