import os,sys
import shutil
from os.path import *
import subprocess
from xml.etree.ElementTree import Element
sys.path.append(os.path.abspath(__file__ + '/../'))
from utils import extract_failing_tests, file_to_ids, mkdir_for_file
from xmlutils import *

all_projects = ['chart', 'cli', 'closure', 'codec', 'collections', 'compress', 'csv', 'gson','jacksoncore',\
    'jacksondatabind', 'jacksonxml', 'jsoup', 'jxpath', 'lang', 'math', 'mockito', 'time']
v1_2_projects = ['chart', 'closure', 'lang', 'math', 'mockito', 'time']
v2_0_add_projects = list(set(all_projects) - set(v1_2_projects))
add_projects = ['chart', 'closure', 'codec', 'collections', 'compress','jacksoncore', 'jacksondatabind', 'jacksonxml', 'jxpath', 'lang', 'math', 'time']
class Defects4J:
    def __init__(self, project_id:str, bug_id:str, buggy_or_fixed:str, proj_home:str): #project_id: chart, bug_id: 1
        self.project_id = project_id
        self.bug_id = bug_id
        self.buggy_or_fixed = buggy_or_fixed
        assert buggy_or_fixed == 'fixed' or buggy_or_fixed == 'buggy'
        self.proj_home = proj_home
        self.failing_tests_path = join(proj_home, 'failing_tests')
        self.all_tests_path = join(proj_home, 'all_tests')
        self.timeout_path = join(proj_home, 'test_timeout')
        self.rela_src_dir_path = get_fst_v_src_rela_dir(project_id)
        self.properties = {}

    def checkout(self): # check out to fixed version
        if not isdir(self.proj_home):
            os.makedirs(self.proj_home)
        cmd = "defects4j checkout -p {0} -v {1}{2} -w {3}"\
            .format(get_identifier_from_lower_name(self.project_id), self.bug_id,\
                    'f' if self.buggy_or_fixed == 'fixed' else 'b', self.proj_home)
        self.run_cmd(cmd, at_home=False)
        self.load_properties()
        
    def checkout_if_not_exist_or_empty(self):
        if not isdir(self.proj_home) or len(os.listdir(self.proj_home)) == 0:
            self.checkout()
        else:
            self.load_properties()
            
    def switch_to_buggy_version(self):
        self.clean()
        self.buggy_or_fixed = 'buggy'
        self.checkout()
    
    def switch_to_fixed_version(self):
        self.clean()
        self.buggy_or_fixed = 'fixed'
        self.checkout()
        
    def export_oracle_patch(self, target_dir):
        ori_state = self.buggy_or_fixed
        self.clean()
        if ori_state == 'buggy':
            self.switch_to_fixed_version()
        patch_rela_path = self.get_buggy_class_name().replace('.', '/') + '.java'
        patch_path = self.get_buggy_file_path()
        assert isfile(patch_path), patch_path
        target_path = join(target_dir, self.project_id, self.bug_id, 'patch', patch_rela_path)
        mkdir_for_file(target_path)
        shutil.copyfile(patch_path, target_path)
        if ori_state == 'buggy':
            self.switch_to_buggy_version()
        self.buggy_or_fixed = ori_state
        
    def export_buggy_file(self, target_dir):
        ori_state = self.buggy_or_fixed
        self.clean()
        if ori_state == 'fixed':
            self.switch_to_buggy_version()
        buggy_rela_path = self.get_buggy_class_name().replace('.', '/') + '.java'
        buggy_path = self.get_buggy_file_path()
        assert isfile(buggy_path), buggy_path
        target_path = join(target_dir, self.project_id, self.bug_id, 'buggy', buggy_rela_path)
        mkdir_for_file(target_path)
        shutil.copyfile(buggy_path, target_path)
        if ori_state == 'fixed':
            self.switch_to_fixed_version()
        self.buggy_or_fixed = ori_state
        
    def get_buggy_file_path(self):
        # assume there's only one buggy file
        buggy_rela_path = self.get_buggy_class_name().replace('.', '/') + '.java'
        buggy_file_path = join(self.proj_home, self.get_property('d4j.dir.src.classes'), buggy_rela_path)
        assert isfile(buggy_file_path), buggy_file_path
        return buggy_file_path
    
    def get_buggy_class_name(self):
        # assume there's only one buggy file
        return self.get_property('d4j.classes.modified')

    def load_properties(self):
        with open(join(self.proj_home, 'defects4j.build.properties')) as f:
            lines = f.readlines()
        for line in lines[1:]:
            if '=' in line:
                line = line.strip()
                key, value = line.split('=')
                self.properties[key] = value
                
    def get_property(self, key):
        try:
            return self.properties[key]
        except KeyError:
            print('KeyError: {}'.format(key))
            sys.exit(1)
    
    def compile(self):
        cmd = "defects4j compile"
        self.run_cmd(cmd)

    def test(self, timeout=300):
        cmd = "timeout {} defects4j test".format(timeout)
        self.run_cmd(cmd)
        
    def test_with_report(self):
        self.test()
        failing_test_report_path = join(self.proj_home, 'failing_tests')
        with open(failing_test_report_path) as f:
            lines = f.readlines()
        failing_test = extract_failing_tests(failing_test_report_path)[0]
        if '::' in failing_test:
            failing_test = failing_test.split('::')[1]
        fail_diag = lines[1].strip()
        
        return failing_test, fail_diag

    def backup(self, src_rela_file_path):
        file_path = join(self.proj_home, self.rela_src_dir_path, src_rela_file_path)
        os.rename(file_path, file_path + '.bak')

    def restore(self, src_rela_file_path):
        file_path = join(self.proj_home, self.rela_src_dir_path, src_rela_file_path)
        os.rename(file_path + '.bak', file_path)

    def run_cmd(self, cmd, output_dir=None, at_home=True):
        if at_home:
            self.cd_home()
        if output_dir is None:
            output_dir = self.proj_home
        print("[DEFECTS4J]running cmd: " + cmd)
        if sys.version.startswith('3.6'):
            result = subprocess.run(cmd.split(' '), stdout=subprocess.PIPE, check=True).stdout.decode('utf-8')
            print(result)
            return result
        result = subprocess.run(cmd, shell=True, capture_output=True)
        print(result.stdout.decode('utf-8'))
        print(result.stderr.decode('utf-8'))
        result.check_returncode()
        return result.stdout.decode('utf-8')
    
    def cd_home(self):
        os.chdir(self.proj_home)
        
    def clean(self, reset_git=True):
        if reset_git:
            self.git_reset()
        cmd = "git clean -f -d"
        self.run_cmd(cmd)
    
    def git_reset(self):
        cmd = "git reset --hard"
        self.run_cmd(cmd)
    
    def get_rela_src_path(self):
        if self.project_id != 'mockito' and self.project_id != 'collections' and self.bug_id == '1':
            return get_fst_v_src_rela_dir(self.project_id)
        elif self.project_id == 'mockito' and self.bug_id == '13':
            return get_fst_v_src_rela_dir(self.project_id)
        elif self.project_id == 'collections' and self.bug_id == '25':
            return get_fst_v_src_rela_dir(self.project_id)
        else:
            return self.get_property('d4j.dir.src.classes')

    def get_rela_bin_path(self):
        cmd = "defects4j export -p dir.bin.classes"
        return self.run_cmd(cmd).strip()
    
    def get_rela_src_tests_path(self):
        return self.get_property('d4j.dir.src.tests')
    
    def get_rela_bin_tests_path(self):
        cmd = "defects4j export -p dir.bin.tests"
        return self.run_cmd(cmd).strip()

    def get_trigger_tests(self):
        return self.get_property('d4j.tests.trigger').split(',')
    
    def get_fst_v_rela_bin_tests_path(self):
        # bin tests information for 1f
        bin_tests_info = {}
        bin_tests_info['chart'] = 'build-tests'
        bin_tests_info['cli'] = 'target/test-classes'
        bin_tests_info['closure'] = 'build/test'
        bin_tests_info['codec'] = 'target/tests'
        bin_tests_info['collections'] = 'target/tests'
        bin_tests_info['compress'] = 'target/test-classes'
        bin_tests_info['csv'] = 'target/test-classes'
        bin_tests_info['gson'] = 'target/test-classes'
        bin_tests_info['jacksoncore'] = 'target/test-classes'
        bin_tests_info['jacksondatabind'] = 'target/test-classes'
        bin_tests_info['jacksonxml'] = 'target/test-classes'
        bin_tests_info['jsoup'] = 'target/test-classes'
        bin_tests_info['jxpath'] = 'target/test-classes'
        bin_tests_info['lang'] = 'target/tests'
        bin_tests_info['math'] = 'target/test-classes'
        bin_tests_info['mockito'] = 'target/test-classes'
        bin_tests_info['time'] = 'target/test-classes'
        return bin_tests_info[self.project_id]
    
    def apply_mutant_file(self, mutant_file_path, rela_path, src_rela_dir=None):
        if src_rela_dir is None:
            src_rela_dir = self.get_rela_src_path()
        target_file_path = join(self.proj_home, src_rela_dir, rela_path)
        assert isfile(mutant_file_path), 'mutant file not found: ' + mutant_file_path
        assert isfile(target_file_path) or isfile(target_file_path + '.bak'), 'target file not found: ' + target_file_path
        shutil.copyfile(mutant_file_path, target_file_path)
    
    def add_pom(self, src_pom_path='', src_pom_root_dir='', tgt_pom_name='pom_configured.xml'):
        # will copy pom file from pom database to project directory
        assert isfile(src_pom_path) or isdir(src_pom_root_dir), 'pom_path or pom_root_dir must be specified'
        assert tgt_pom_name.endswith('.xml'), 'pom_name must end with .xml'
        if self.project_id == 'gson':
            tgt_pom_name = 'gson/' + tgt_pom_name
        if isfile(src_pom_path):
            cmd = "cp {0} {1}".format(src_pom_path, tgt_pom_name)
        elif isdir(src_pom_root_dir):
            cmd = "cp {0} {1}".format(join(src_pom_root_dir, self.project_id + '-1f', 'pom.xml'), tgt_pom_name)
        self.run_cmd(cmd)
        
    def add_pom_with_perfect_fl(self, oracle_faulty_class, oracle_faulty_line, failing_tests, src_pom_path='', src_pom_root_dir='', tgt_pom_name='pom_for_prapr_perfect.xml'):
        # will modify the copied pom file and insert prapr plugin with perfect fault localization information
        assert isfile(src_pom_path) or isdir(src_pom_root_dir), 'pom_path or pom_root_dir must be specified'
        assert tgt_pom_name.endswith('.xml'), 'pom_name must end with .xml'
        if self.project_id == 'gson':
            tgt_pom_name = 'gson/' + tgt_pom_name
        if isfile(src_pom_path):
            ori_pom_path = src_pom_path
        elif isdir(src_pom_root_dir):
            ori_pom_path = join(src_pom_root_dir, self.project_id + '-1f', 'pom.xml')
            
        def get_prapr_childnode_filter(root:Element,node:Element):
            parent_node = get_parent_node(root, node)
            return parent_node.tag == pom_prefix + 'plugin' and \
                parent_node.find(pom_prefix + 'artifactId').text == 'prapr-plugin' and \
                    parent_node.find(pom_prefix + 'groupId').text == 'org.mudebug'
                
        
        add_node_list_for_target(ori_pom_path, 'configuration', get_prapr_childnode_filter, 'failingTests', failing_tests, tgt_doc_path=join(self.proj_home, tgt_pom_name))
        add_node_for_target(join(self.proj_home, tgt_pom_name), 'configuration', get_prapr_childnode_filter, 'oracleFaultyClass', oracle_faulty_class)
        add_node_for_target(join(self.proj_home, tgt_pom_name), 'configuration', get_prapr_childnode_filter, 'oracleFaultyLine', oracle_faulty_line)

    def add_pom_with_perfect_fl_from_scratch(self, oracle_faulty_class, oracle_faulty_line, failing_tests, src_pom_path='', src_pom_root_dir='', tgt_pom_name='pom_for_prapr_perfect.xml'):
        # will generate a new pom from scratch and insert prapr plugin with perfect fault localization information
        assert isfile(src_pom_path) or isdir(src_pom_root_dir), 'pom_path or pom_root_dir must be specified'
        assert tgt_pom_name.endswith('.xml'), 'pom_name must end with .xml'
        if self.project_id == 'gson':
            tgt_pom_name = 'gson/' + tgt_pom_name
        if isfile(src_pom_path):
            ori_pom_path = src_pom_path
        elif isdir(src_pom_root_dir):
            ori_pom_path = join(src_pom_root_dir, self.project_id + '-1f', 'pom.xml')
            assert False, 'not implemented yet'
            
        def get_prapr_plugin_filter(root:Element,node:Element):
            return node.tag == pom_prefix + 'plugin' and \
                node.find(pom_prefix + 'artifactId').text == 'prapr-plugin' and \
                    node.find(pom_prefix + 'groupId').text == 'org.mudebug'
                    
        def get_prapr_childnode_filter(root:Element,node:Element):
            parent_node = get_parent_node(root, node)
            return parent_node.tag == pom_prefix + 'plugin' and \
                parent_node.find(pom_prefix + 'artifactId').text == 'prapr-plugin' and \
                    parent_node.find(pom_prefix + 'groupId').text == 'org.mudebug'
        
        def get_junit_dependency_child_filter(root:Element, node:Element):
            parent_node = get_parent_node(root, node)
            return parent_node.tag == pom_prefix + 'dependency' and \
                parent_node.find(pom_prefix + 'artifactId').text == 'junit' and \
                    parent_node.find(pom_prefix + 'groupId').text == 'junit'
                
        tgt_pom_path = join(self.proj_home, tgt_pom_name)
        plugins_node, tree = delete_node(ori_pom_path, 'plugin', get_prapr_plugin_filter, tgt_pom_path)
        build_node = None
        if plugins_node is None:
            for child_node in tree.getroot():
                if child_node.tag == pom_prefix + 'build':
                    build_node = child_node
                    plugins_node = build_node.find(pom_prefix + 'plugins')
                    # parent_node = plugins_node
                    break
        if plugins_node is None:
            # cannot find build/plugins node in pom, create one
            if build_node is None:
                build_node = ET.SubElement(tree.getroot(), 'build')
            plugins_node = ET.SubElement(build_node, 'plugins')
            
        prapr_plugin_node = ET.SubElement(plugins_node, 'plugin')
        group_id_node = ET.SubElement(prapr_plugin_node, 'groupId')
        artifact_id_node = ET.SubElement(prapr_plugin_node, 'artifactId')
        version_node = ET.SubElement(prapr_plugin_node, 'version')
        config_node = ET.SubElement(prapr_plugin_node, 'configuration')
        threads_node = ET.SubElement(config_node, 'threads')
        mutators_node = ET.SubElement(config_node, 'mutators')
        mutator_node = ET.SubElement(mutators_node, 'mutator')
        mutator_node.text = 'ALL'
        output_formats_node = ET.SubElement(config_node, 'outputFormats')
        output_format_node_1 = ET.SubElement(output_formats_node, 'param')
        output_format_node_1.text = 'COMPRESSED-XML'
        output_format_node_2 = ET.SubElement(output_formats_node, 'param')
        output_format_node_2.text = 'LOG'
        threads_node.text = '1'
        group_id_node.text = 'org.mudebug'
        artifact_id_node.text = 'prapr-plugin'
        version_node.text = '2.0.3-SNAPSHOT'
        tree.write(tgt_pom_path)
        
        add_node_list_for_target(tgt_pom_path, 'configuration', get_prapr_childnode_filter, 'failingTests', failing_tests)
        add_node_for_target(tgt_pom_path, 'configuration', get_prapr_childnode_filter, 'oracleFaultyClass', oracle_faulty_class)
        add_node_for_target(tgt_pom_path, 'configuration', get_prapr_childnode_filter, 'oracleFaultyLine', oracle_faulty_line)
        
        update_node_for_target(tgt_pom_path, 'version', get_junit_dependency_child_filter, '4.12')
        
    def run_prapr(self, pom_name='pom_for_prapr.xml', mutate_susp_stmt='DEFAULT'):
        if self.project_id == 'gson':
            pom_name = 'gson/' + pom_name
        cmd = "mvn org.mudebug:prapr-plugin:2.0.3-SNAPSHOT:prapr -Dhttps.protocols=TLSv1.2 -f {0}".format(pom_name)
        if self.project_id.startswith('jackson'):
            cmd += ' -Denforcer.skip'
        cmd += (' -DmutateSuspStmt=' + mutate_susp_stmt)
        
        cmd += ' -Dhttps.protocols=TLSv1.2' # this aims to solve the protocol problem of downloading dependencies from maven central
        self.run_cmd(cmd)
    
def checkout_all_first_fixed(repo_root_dir, projects=['chart', 'cli', 'codec', 'compress', 'csv', 'gson','jacksoncore',\
    'jacksondatabind', 'jacksonxml', 'jsoup', 'jxpath', 'lang', 'math', 'time', 'mockito', 'closure', 'collections']):
    
    assert isdir(repo_root_dir) and isabs(repo_root_dir), 'repo_root_dir must be an absolute path'
    for project in projects:
        bug_id = '13' if project == 'mockito' else '25' if project == 'collections' else '1'
        d4j_proj = Defects4J(project, bug_id, 'fixed', join(repo_root_dir, project + '-1f'))
        d4j_proj.checkout()
        
def get_fst_v_src_rela_dir(proj):
    # return the relative path of source code directory of the first version of the project (13 for mockito, 25 for collections, 1 for others)
    src_info = {}
    src_info['chart'] = 'source'
    src_info['cli'] = 'src/java'
    src_info['closure'] = 'src'
    src_info['codec'] = 'src/java'
    src_info['collections'] = 'src/main/java'
    src_info['compress'] = 'src/main/java'
    src_info['csv'] = 'src/main/java'
    src_info['gson'] = 'gson/src/main/java'
    src_info['jacksoncore'] = 'src/main/java'
    src_info['jacksondatabind'] = 'src/main/java'
    src_info['jacksonxml'] = 'src/main/java'
    src_info['jsoup'] = 'src/main/java'
    src_info['jxpath'] = 'src/java'
    src_info['lang'] = 'src/main/java'
    src_info['math'] = 'src/main/java'
    src_info['mockito'] = 'src'
    src_info['time'] = 'src/main/java'
    
    return src_info[proj]

def get_identifier_from_lower_name(proj_lower_name:str) -> str:
    if proj_lower_name in ['jacksoncore', 'jacksondatabind', 'jacksonxml', 'jxpath']:
        if proj_lower_name == 'jacksoncore':
            return 'JacksonCore'
        elif proj_lower_name == 'jacksondatabind':
            return 'JacksonDatabind'
        elif proj_lower_name == 'jacksonxml':
            return 'JacksonXml'
        elif proj_lower_name == 'jxpath':
            return 'JxPath'
    else:
        return proj_lower_name.capitalize()