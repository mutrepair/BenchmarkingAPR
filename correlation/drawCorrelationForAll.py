import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd
import scipy, math

# plt.rcParams["font.family"] = "Times"
font = {'family' : 'Times',
        'weight' : 'bold',
        'size'   : 22}
plt.rc('font', **font)

def myround(n):
    n = float(n)
    if n == 0:
        return 0
    sgn = -1 if n < 0 else 1
    scale = int(-math.floor(math.log10(abs(n))))
    if scale <= 0:
        scale = 1
    factor = 10**scale
    return sgn*math.floor(abs(n)*factor)/factor

def tcePatchRatioCorrelation():
    patchNum = {'Ratio of TCE Patches':[0.44,1.75,0.54,0.83,0.32,0.17,0.25,0.05,0.04,0.35,0.15,1.27], 'Ratio of Genuine Patches':[0.54,1.88,1.13,1.16,0.54,0.50,0.40,0.05,0.05,0.49,0.16,2.35]}
    xLabel = 'Ratio of TCE Patches'
    yLabel = 'Ratio of Genuine Patches'
    patchNum[xLabel] = [a/100 for a in patchNum[xLabel]]
    patchNum[yLabel] = [a/100 for a in patchNum[yLabel]]
    x = patchNum[xLabel]
    y = patchNum[yLabel]
    # calculate pearson correlation coefficient
    r, p = scipy.stats.pearsonr(x, y)

    f, ax = plt.subplots(figsize=(5.5,5.5))
    df = pd.DataFrame(patchNum)
    plt.text(0.1, 0.9, 'R = {}'.format(round(r, 3)), transform=ax.transAxes)
    plt.text(0.1, 0.8, 'p = {}'.format(("%.17f" % myround(p)).rstrip('0').rstrip('.')), transform=ax.transAxes)
    sns.regplot(x = xLabel, y = yLabel, data = df, 
                marker='*', color='red', scatter_kws={'s':75})
    # plt.show()
    return f

def tceBugRatioCorrelation():
    bugsNum = {'Ratio of Bugs w/ TCE Patches': [27.14,25.00,34.29,31.43,18.57,10.71,19.29,4.29,3.57,25.71,12.14,14.29], 'Ratio of Bugs w/ Genuine Patches': [28.57,28.57,35.71,32.14,19.29,12.14,22.14,4.29,4.29,28.57,12.14,26.43]}
    xLabel = 'Ratio of Bugs w/ TCE Patches'
    yLabel = 'Ratio of Bugs w/ Genuine Patches'
    bugsNum[xLabel] = [a/100 for a in bugsNum[xLabel]]
    bugsNum[yLabel] = [a/100 for a in bugsNum[yLabel]]
    x = bugsNum[xLabel]
    y = bugsNum[yLabel]
    # calculate pearson correlation coefficient
    r, p = scipy.stats.pearsonr(x, y)

    f, ax = plt.subplots(figsize=(5.5,5.5))
    df = pd.DataFrame(bugsNum)
    plt.text(0.1, 0.9, 'R = {}'.format(round(r, 3)), transform=ax.transAxes)
    plt.text(0.1, 0.8, 'p = {}'.format(("%.17f" % myround(p)).rstrip('0').rstrip('.')), transform=ax.transAxes)
    sns.regplot(x = xLabel, y = yLabel, data = df, 
                marker='*', color='red', scatter_kws={'s':75})
    # plt.show()
    return f

def sePatchRatioCorrelation():
    patchNum = {'Ratio of SE Patches':[0.22,1.65,0.33,0.61,0.17,0.15,0.19,0.04,0.02,0.32,0.14], 'Ratio of Genuine Patches':[0.54,1.88,1.13,1.16,0.54,0.50,0.40,0.05,0.05,0.49,0.16]}
    xLabel = 'Ratio of SE Patches'
    yLabel = 'Ratio of Genuine Patches'
    patchNum[xLabel] = [a/100 for a in patchNum[xLabel]]
    patchNum[yLabel] = [a/100 for a in patchNum[yLabel]]
    x = patchNum[xLabel]
    y = patchNum[yLabel]
    # calculate pearson correlation coefficient
    r, p = scipy.stats.pearsonr(x, y)

    f, ax = plt.subplots(figsize=(5.5,5.5))
    df = pd.DataFrame(patchNum)
    plt.text(0.1, 0.9, 'R = {}'.format(round(r, 3)), transform=ax.transAxes)
    plt.text(0.1, 0.8, 'p = {}'.format(("%.17f" % myround(p)).rstrip('0').rstrip('.')), transform=ax.transAxes)
    sns.regplot(x = xLabel, y = yLabel, data = df, 
                marker='*', color='red', scatter_kws={'s':75})
    # plt.show()
    return f

def seBugRatioCorrelation():
    bugsNum = {'Ratio of Bugs w/ SE Patches': [13.57,23.57,32.14,29.29,12.14,10.71,18.57,3.57,2.14,24.29,11.43], 'Ratio of Bugs w/ Genuine Patches': [28.57,28.57,35.71,32.14,19.29,12.14,22.14,4.29,4.29,28.57,12.14]}
    xLabel = 'Ratio of Bugs w/ SE Patches'
    yLabel = 'Ratio of Bugs w/ Genuine Patches'
    bugsNum[xLabel] = [a/100 for a in bugsNum[xLabel]]
    bugsNum[yLabel] = [a/100 for a in bugsNum[yLabel]]
    x = bugsNum[xLabel]
    y = bugsNum[yLabel]
    # calculate pearson correlation coefficient
    r, p = scipy.stats.pearsonr(x, y)

    f, ax = plt.subplots(figsize=(5.5,5.5))
    df = pd.DataFrame(bugsNum)
    plt.text(0.1, 0.9, 'R = {}'.format(round(r, 3)), transform=ax.transAxes)
    plt.text(0.1, 0.8, 'p = {}'.format(("%.17f" % myround(p)).rstrip('0').rstrip('.')), transform=ax.transAxes)
    sns.regplot(x = xLabel, y = yLabel, data = df, 
                marker='*', color='red', scatter_kws={'s':75})
    # plt.show()
    return f

def plausiblePatchRatioCorrelation():
    patchNum = {'Ratio of Plausible Patches':[1.96,4.70,4.35,4.29,2.65,1.90,2.24,0.17,0.35,3.46,2.77,7.80], 'Ratio of Genuine Patches':[0.54,1.88,1.13,1.16,0.54,0.50,0.40,0.05,0.05,0.49,0.16,2.35]}
    xLabel = 'Ratio of Plausible Patches'
    yLabel = 'Ratio of Genuine Patches'
    patchNum[xLabel] = [a/100 for a in patchNum[xLabel]]
    patchNum[yLabel] = [a/100 for a in patchNum[yLabel]]
    x = patchNum[xLabel]
    y = patchNum[yLabel]
    # calculate pearson correlation coefficient
    r, p = scipy.stats.pearsonr(x, y)

    f, ax = plt.subplots(figsize=(5.5,5.5))
    df = pd.DataFrame(patchNum)
    plt.text(0.1, 0.9, 'R = {}'.format(round(r, 3)), transform=ax.transAxes)
    plt.text(0.1, 0.8, 'p = {}'.format(("%.17f" % myround(p)).rstrip('0').rstrip('.')), transform=ax.transAxes)
    sns.regplot(x = xLabel, y = yLabel, data = df, 
                marker='*', color='red', scatter_kws={'s':75})
    # plt.show()
    return f

def plausibleBugRatioCorrelation():
    bugsNum = {'Ratio of Bugs w/ Plausible Patches': [40.00,47.86,52.14,50.00,31.43,22.14,37.14,6.43,12.14,46.43,18.57,38.57], 'Ratio of Bugs w/ Genuine Patches': [28.57,28.57,35.71,32.14,19.29,12.14,22.14,4.29,4.29,28.57,12.14,26.43]}
    xLabel = 'Ratio of Bugs w/ Plausible Patches'
    yLabel = 'Ratio of Bugs w/ Genuine Patches'
    bugsNum[xLabel] = [a/100 for a in bugsNum[xLabel]]
    bugsNum[yLabel] = [a/100 for a in bugsNum[yLabel]]
    x = bugsNum[xLabel]
    y = bugsNum[yLabel]
    # calculate pearson correlation coefficient
    r, p = scipy.stats.pearsonr(x, y)

    f, ax = plt.subplots(figsize=(5.5,5.5))
    df = pd.DataFrame(bugsNum)
    plt.text(0.1, 0.9, 'R = {}'.format(round(r, 3)), transform=ax.transAxes)
    plt.text(0.1, 0.8, 'p = {}'.format(("%.17f" % myround(p)).rstrip('0').rstrip('.')), transform=ax.transAxes)
    sns.regplot(x = xLabel, y = yLabel, data = df, 
                marker='*', color='red', scatter_kws={'s':75})
    # plt.show()
    return f

def compPatchRatioCorrelation():
    patchNum = {'Ratio of Compilable Patches':[31.88,40.49,27.76,35.28,25.15,22.34,26.27,2.19,0.01,61.15,23.47], 'Ratio of Genuine Patches':[0.54,1.88,1.13,1.16,0.54,0.50,0.40,0.05,0.05,0.49,0.16]}
    xLabel = 'Ratio of Compilable Patches'
    yLabel = 'Ratio of Genuine Patches'
    patchNum[xLabel] = [a/100 for a in patchNum[xLabel]]
    patchNum[yLabel] = [a/100 for a in patchNum[yLabel]]
    x = patchNum[xLabel]
    y = patchNum[yLabel]
    # calculate pearson correlation coefficient
    r, p = scipy.stats.pearsonr(x, y)

    f, ax = plt.subplots(figsize=(5.5,5.5))
    df = pd.DataFrame(patchNum)
    plt.text(0.1, 0.9, 'R = {}'.format(round(r, 3)), transform=ax.transAxes)
    plt.text(0.1, 0.8, 'p = {}'.format(("%.17f" % myround(p)).rstrip('0').rstrip('.')), transform=ax.transAxes)
    sns.regplot(x = xLabel, y = yLabel, data = df, 
                marker='*', color='red', scatter_kws={'s':75})
    # plt.show()
    return f

def compBugRatioCorrelation():
    bugsNum = {'Ratio of Bugs w/ Compilable Patches': [89.29,96.43,91.43,97.14,75.71,83.57,90.00,33.57,0.71,91.43,63.57], 'Ratio of Bugs w/ Genuine Patches': [28.57,28.57,35.71,32.14,19.29,12.14,22.14,4.29,4.29,28.57,12.14]}
    xLabel = 'Ratio of Bugs w/ Compilable Patches'
    yLabel = 'Ratio of Bugs w/ Genuine Patches'
    bugsNum[xLabel] = [a/100 for a in bugsNum[xLabel]]
    bugsNum[yLabel] = [a/100 for a in bugsNum[yLabel]]
    x = bugsNum[xLabel]
    y = bugsNum[yLabel]
    # calculate pearson correlation coefficient
    r, p = scipy.stats.pearsonr(x, y)

    f, ax = plt.subplots(figsize=(5.5,5.5))
    df = pd.DataFrame(bugsNum)
    plt.text(0.1, 0.9, 'R = {}'.format(round(r, 3)), transform=ax.transAxes)
    plt.text(0.1, 0.8, 'p = {}'.format(("%.17f" % myround(p)).rstrip('0').rstrip('.')), transform=ax.transAxes)
    sns.regplot(x = xLabel, y = yLabel, data = df, 
                marker='*', color='red', scatter_kws={'s':75})
    # plt.show()
    return f

def compilableNumFixNumCorrelation():
    bugsNum = {'# Compilable Patches': [2795, 5669, 3730, 4939, 2561, 2882, 3652, 291, 1427, 6439, 2699], '# Bugs w/ Genuine Patches': [40, 40, 50, 45, 27, 17, 31, 6, 6, 40, 17]}
    xLabel = '# Compilable Patches'
    yLabel = '# Bugs w/ Genuine Patches'
    x = bugsNum[xLabel]
    y = bugsNum[yLabel]
    # calculate pearson correlation coefficient
    r, p = scipy.stats.pearsonr(x, y)

    f, ax = plt.subplots(figsize=(8,5))
    df = pd.DataFrame(bugsNum)
    plt.text(0.1, 0.9, 'R = {}'.format(round(r, 3)), transform=ax.transAxes)
    plt.text(0.1, 0.8, 'p = {}'.format(("%.17f" % myround(p)).rstrip('0').rstrip('.')), transform=ax.transAxes)
    sns.regplot(x = xLabel, y = yLabel, data = df, 
                marker='*', color='red', scatter_kws={'s':75})
    # plt.show()
    return f

if __name__ == '__main__':
    tcePatchRatioCorrelation().savefig("tcePatchNumCorrelation.pdf", bbox_inches='tight')
    tceBugRatioCorrelation().savefig("tceBugNumCorrelation.pdf", bbox_inches='tight')
    sePatchRatioCorrelation().savefig("sePatchNumCorrelation.pdf", bbox_inches='tight')
    seBugRatioCorrelation().savefig("seBugNumCorrelation.pdf", bbox_inches='tight')
    plausiblePatchRatioCorrelation().savefig("plausiblePatchRatioCorrelation.pdf", bbox_inches='tight')
    plausibleBugRatioCorrelation().savefig("plausibleBugRatioCorrelation.pdf", bbox_inches='tight')
    # compBugRatioCorrelation().savefig("compBugRatioCorrelation.pdf", bbox_inches='tight')
    # compPatchRatioCorrelation().savefig("compPatchRatioCorrelation.pdf", bbox_inches='tight')
    compilableNumFixNumCorrelation().savefig("compilableNumFixNumCorrelation.pdf", bbox_inches='tight')