# import pandas lib as pd
import pandas as pd

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('opaques_en.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('opaques_en.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE OPAQUES_EN;"+"\n")
    f.write("CREATE TABLE OPAQUES_EN(ID TEXT,MINERAL TEXT,FOTO TEXT,MINERAL2 TEXT,FORMULA TEXT,CLEAVAGE TEXT,REFLECTANCE TEXT,COLORS TEXT,HARDNESS TEXT,PLEOCHROISM TEXT,ANISOTROPISM TEXT,INFERENCECOLORS TEXT,REFELCTIONS TEXT,ASSOCIATION TEXT,MORPHOLOGY TEXT,CLEAVAGE2 TEXT,REFLECTANCE2,COLORS2 TEXT,HARDNESS2 TEXT,PLEOCHROISM2 TEXT,ANISOTROPISM2 TEXT,INFERENCECOLORS2 TEXT,REFLECTIONS2 TEXT,ABUNDANCE2 TEXT,OTHERS TEXT,URLICON1 TEXT,URLICON2 TEXT,URL1 TEXT,URL2 TEXT);"+"\n")
    for i in range (0,15):
        f.write('INSERT INTO OPAQUES_EN VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'","'+str(datos[i][9])+'","'+str(datos[i][10])+'","'+str(datos[i][11])+'","'+str(datos[i][12])+'","'+str(datos[i][13])+'","'+str(datos[i][14])+'","'+str(datos[i][15])+'","'+str(datos[i][16])+'","'+str(datos[i][17])+'","'+str(datos[i][18])+'","'+str(datos[i][19])+'","'+str(datos[i][20])+'","'+str(datos[i][21])+'","'+str(datos[i][22])+'","'+str(datos[i][23])+'%","'+str(datos[i][24])+'","'+str(datos[i][25])+'","'+str(datos[i][26])+'","'+str(datos[i][27])+'","'+str(datos[i][28])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('opaques_es.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('opaques_es.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE OPAQUES_ES;"+"\n")
    f.write("CREATE TABLE OPAQUES_ES(ID TEXT,MINERAL TEXT,FOTO TEXT,MINERAL2 TEXT,FORMULA TEXT,CLEAVAGE TEXT,REFLECTANCE TEXT,COLORS TEXT,HARDNESS TEXT,PLEOCHROISM TEXT,ANISOTROPISM TEXT,INFERENCECOLORS TEXT,REFELCTIONS TEXT,ASSOCIATION TEXT,MORPHOLOGY TEXT,CLEAVAGE2 TEXT,REFLECTANCE2,COLORS2 TEXT,HARDNESS2 TEXT,PLEOCHROISM2 TEXT,ANISOTROPISM2 TEXT,INFERENCECOLORS2 TEXT,REFLECTIONS2 TEXT,ABUNDANCE2 TEXT,OTHERS TEXT,URLICON1 TEXT,URLICON2 TEXT,URL1 TEXT,URL2 TEXT);"+"\n")
    for i in range (0,15):
        f.write('INSERT INTO OPAQUES_ES VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'","'+str(datos[i][9])+'","'+str(datos[i][10])+'","'+str(datos[i][11])+'","'+str(datos[i][12])+'","'+str(datos[i][13])+'","'+str(datos[i][14])+'","'+str(datos[i][15])+'","'+str(datos[i][16])+'","'+str(datos[i][17])+'","'+str(datos[i][18])+'","'+str(datos[i][19])+'","'+str(datos[i][20])+'","'+str(datos[i][21])+'","'+str(datos[i][22])+'","'+str(datos[i][23])+'%","'+str(datos[i][24])+'","'+str(datos[i][25])+'","'+str(datos[i][26])+'","'+str(datos[i][27])+'","'+str(datos[i][28])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('opaques_ca.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('opaques_ca.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE OPAQUES_CA;"+"\n")
    f.write("CREATE TABLE OPAQUES_CA(ID TEXT,MINERAL TEXT,FOTO TEXT,MINERAL2 TEXT,FORMULA TEXT,CLEAVAGE TEXT,REFLECTANCE TEXT,COLORS TEXT,HARDNESS TEXT,PLEOCHROISM TEXT,ANISOTROPISM TEXT,INFERENCECOLORS TEXT,REFELCTIONS TEXT,ASSOCIATION TEXT,MORPHOLOGY TEXT,CLEAVAGE2 TEXT,REFLECTANCE2,COLORS2 TEXT,HARDNESS2 TEXT,PLEOCHROISM2 TEXT,ANISOTROPISM2 TEXT,INFERENCECOLORS2 TEXT,REFLECTIONS2 TEXT,ABUNDANCE2 TEXT,OTHERS TEXT,URLICON1 TEXT,URLICON2 TEXT,URL1 TEXT,URL2 TEXT);"+"\n")
    for i in range (0,15):
        f.write('INSERT INTO OPAQUES_CA VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'","'+str(datos[i][9])+'","'+str(datos[i][10])+'","'+str(datos[i][11])+'","'+str(datos[i][12])+'","'+str(datos[i][13])+'","'+str(datos[i][14])+'","'+str(datos[i][15])+'","'+str(datos[i][16])+'","'+str(datos[i][17])+'","'+str(datos[i][18])+'","'+str(datos[i][19])+'","'+str(datos[i][20])+'","'+str(datos[i][21])+'","'+str(datos[i][22])+'","'+str(datos[i][23])+'%","'+str(datos[i][24])+'","'+str(datos[i][25])+'","'+str(datos[i][26])+'","'+str(datos[i][27])+'","'+str(datos[i][28])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('transparents_en.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('transparents_en.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE TRANSPARENTS_EN;"+"\n")
    f.write("CREATE TABLE TRANSPARENTS_EN(ID TEXT,MINERAL TEXT,FOTO TEXT,MINERAL2 TEXT,FORMULA TEXT,SHAPE TEXT,RELIEF TEXT,COLORS TEXT,PLEOCHROISM TEXT,CLEAVAGE TEXT,ALTERATION TEXT,INTERFERENCECOLOR TEXT,EXTINCTION TEXT, TWINNING TEXT, ZONATION TEXT, INTERFERENCEFIGURE TEXT, OPTICALSIGN TEXT,SHAPE2 TEXT,RELIEFS2 TEXT,COLORS2 TEXT,PLEOCHROISM2,CLEAVAGE2 TEXT,ALTERATION2 TEXT,INTERFERENCECOLOR2 TEXT,EXTINCTION2 TEXT,TWINNING2 TEXT,ZONATION2 TEXT,ABUNDANCE TEXT,OTHERS TEXT,URLICON1 TEXT,URLICON2 TEXT,URL1 TEXT,URL2 TEXT);"+"\n")
    for i in range (0,40):
        f.write('INSERT INTO TRANSPARENTS_EN VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'","'+str(datos[i][9])+'","'+str(datos[i][10])+'","'+str(datos[i][11])+'","'+str(datos[i][12])+'","'+str(datos[i][13])+'","'+str(datos[i][14])+'","'+str(datos[i][15])+'","'+str(datos[i][16])+'","'+str(datos[i][17])+'","'+str(datos[i][18])+'","'+str(datos[i][19])+'","'+str(datos[i][20])+'","'+str(datos[i][21])+'","'+str(datos[i][22])+'","'+str(datos[i][23])+'","'+str(datos[i][24])+'","'+str(datos[i][25])+'","'+str(datos[i][26])+'","'+str(datos[i][27])+'%","'+str(datos[i][28])+'","'+str(datos[i][29])+'","'+str(datos[i][30])+'","'+str(datos[i][31])+'","'+str(datos[i][32])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('transparents_es.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('transparents_es.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE TRANSPARENTS_ES;"+"\n")
    f.write("CREATE TABLE TRANSPARENTS_ES(ID TEXT,MINERAL TEXT,FOTO TEXT,MINERAL2 TEXT,FORMULA TEXT,SHAPE TEXT,RELIEF TEXT,COLORS TEXT,PLEOCHROISM TEXT,CLEAVAGE TEXT,ALTERATION TEXT,INTERFERENCECOLOR TEXT,EXTINCTION TEXT, TWINNING TEXT, ZONATION TEXT, INTERFERENCEFIGURE TEXT, OPTICALSIGN TEXT,SHAPE2 TEXT,RELIEFS2 TEXT,COLORS2 TEXT,PLEOCHROISM2,CLEAVAGE2 TEXT,ALTERATION2 TEXT,INTERFERENCECOLOR2 TEXT,EXTINCTION2 TEXT,TWINNING2 TEXT,ZONATION2 TEXT,ABUNDANCE TEXT,OTHERS TEXT,URLICON1 TEXT,URLICON2 TEXT,URL1 TEXT,URL2 TEXT);"+"\n")
    for i in range (0,40):
        f.write('INSERT INTO TRANSPARENTS_ES VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'","'+str(datos[i][9])+'","'+str(datos[i][10])+'","'+str(datos[i][11])+'","'+str(datos[i][12])+'","'+str(datos[i][13])+'","'+str(datos[i][14])+'","'+str(datos[i][15])+'","'+str(datos[i][16])+'","'+str(datos[i][17])+'","'+str(datos[i][18])+'","'+str(datos[i][19])+'","'+str(datos[i][20])+'","'+str(datos[i][21])+'","'+str(datos[i][22])+'","'+str(datos[i][23])+'","'+str(datos[i][24])+'","'+str(datos[i][25])+'","'+str(datos[i][26])+'","'+str(datos[i][27])+'%","'+str(datos[i][28])+'","'+str(datos[i][29])+'","'+str(datos[i][30])+'","'+str(datos[i][31])+'","'+str(datos[i][32])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('transparents_ca.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('transparents_ca.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE TRANSPARENTS_CA;"+"\n")
    f.write("CREATE TABLE TRANSPARENTS_CA(ID TEXT,MINERAL TEXT,FOTO TEXT,MINERAL2 TEXT,FORMULA TEXT,SHAPE TEXT,RELIEF TEXT,COLORS TEXT,PLEOCHROISM TEXT,CLEAVAGE TEXT,ALTERATION TEXT,INTERFERENCECOLOR TEXT,EXTINCTION TEXT, TWINNING TEXT, ZONATION TEXT, INTERFERENCEFIGURE TEXT, OPTICALSIGN TEXT,SHAPE2 TEXT,RELIEFS2 TEXT,COLORS2 TEXT,PLEOCHROISM2,CLEAVAGE2 TEXT,ALTERATION2 TEXT,INTERFERENCECOLOR2 TEXT,EXTINCTION2 TEXT,TWINNING2 TEXT,ZONATION2 TEXT,ABUNDANCE TEXT,OTHERS TEXT,URLICON1 TEXT,URLICON2 TEXT,URL1 TEXT,URL2 TEXT);"+"\n")
    for i in range (0,40):
        f.write('INSERT INTO TRANSPARENTS_CA VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'","'+str(datos[i][9])+'","'+str(datos[i][10])+'","'+str(datos[i][11])+'","'+str(datos[i][12])+'","'+str(datos[i][13])+'","'+str(datos[i][14])+'","'+str(datos[i][15])+'","'+str(datos[i][16])+'","'+str(datos[i][17])+'","'+str(datos[i][18])+'","'+str(datos[i][19])+'","'+str(datos[i][20])+'","'+str(datos[i][21])+'","'+str(datos[i][22])+'","'+str(datos[i][23])+'","'+str(datos[i][24])+'","'+str(datos[i][25])+'","'+str(datos[i][26])+'","'+str(datos[i][27])+'%","'+str(datos[i][28])+'","'+str(datos[i][29])+'","'+str(datos[i][30])+'","'+str(datos[i][31])+'","'+str(datos[i][32])+'");'+"\n")
