import sys, os, re
sys.path.append(os.path.abspath(__file__ + '/../'))

def load_tokens(file_path):
    with open(file_path, 'r') as f:
        lines = f.readlines()
    char_list = []
    for line in lines:
        char_list.append(re.findall('"([^"]*)"', line)[0])
    char_list.sort(key=len, reverse=True)
    
    return char_list


def java_tokenize(line:str, tokens:list):
    def take_apart(string:str, tokens:list):
        string = string.strip()
        for token in tokens:
            if string.startswith(token):
                return [token] + take_apart(string[len(token):], tokens)
        for token in tokens:
            if string.endswith(token):
                return take_apart(string[:-len(token)], tokens) + [token]
        for token in tokens:
            if token in string:
                return take_apart(string.split(token)[0], tokens) + [token] + take_apart(string[string.find(token) + len(token):], tokens)
        return [string]
    
    line = line.strip()
    if '//' in line:
        line = line[:line.find('//')] # remove comments for java
    tokenized_line = []
    for word in line.split():
        if len(word) > 0:
            seperated_words = take_apart(word, tokens)
            if '' in seperated_words:
                seperated_words.remove('')
            tokenized_line += seperated_words
    return tokenized_line