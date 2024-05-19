# import pandas lib as pd
import pandas as pd

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('filtro_opacos_en.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('filtro_opacos_en.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE FILTRO_OPACOS_EN;"+"\n")
    f.write("CREATE TABLE FILTRO_OPACOS_EN(ID TEXT,MINERAL TEXT,COLOR TEXT,REFLECTANCE TEXT,PLEOCHROISM TEXT,POLISHINGHARDNESS TEXT,ANISOTROPISM TEXT,INTERFERENCECOLORS TEXT,INTERNALREFLECTIONS TEXT);"+"\n")
    for i in range (0,35):
        f.write('INSERT INTO FILTRO_OPACOS_EN VALUES("'+str(int(datos[i][0]))+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('filtro_opacos_es.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('filtro_opacos_es.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE FILTRO_OPACOS_ES;"+"\n")
    f.write("CREATE TABLE FILTRO_OPACOS_ES(ID TEXT,MINERAL TEXT,COLOR TEXT,REFLECTANCE TEXT,PLEOCHROISM TEXT,POLISHINGHARDNESS TEXT,ANISOTROPISM TEXT,INTERFERENCECOLORS TEXT,INTERNALREFLECTIONS TEXT);"+"\n")
    for i in range (0,35):
        f.write('INSERT INTO FILTRO_OPACOS_ES VALUES("'+str(int(datos[i][0]))+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('filtro_opacos_ca.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('filtro_opacos_ca.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE FILTRO_OPACOS_CA;"+"\n")
    f.write("CREATE TABLE FILTRO_OPACOS_CA(ID TEXT,MINERAL TEXT,COLOR TEXT,REFLECTANCE TEXT,PLEOCHROISM TEXT,POLISHINGHARDNESS TEXT,ANISOTROPISM TEXT,INTERFERENCECOLORS TEXT,INTERNALREFLECTIONS TEXT);"+"\n")
    for i in range (0,35):
        f.write('INSERT INTO FILTRO_OPACOS_CA VALUES("'+str(int(datos[i][0]))+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'");'+"\n")


# read by default 1st sheet of an excel file
dataframe = pd.read_excel('filtro_transparentes_en.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('filtro_transparentes_en.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE FILTRO_TRANSPARENTES_EN;"+"\n")
    f.write("CREATE TABLE FILTRO_TRANSPARENTES_EN(ID TEXT,MINERAL TEXT,RELIEF TEXT,COLOR TEXT,PLEOCHROISM TEXT,NUMBERCLEAVAGEDIRECTIONS TEXT,ANGLEOFCLEAVAGE TEXT,INTERFERENCECOLOR TEXT,EXTINCTION TEXT,TWINNING TEXT,INTERFERENCEFIGURE TEXT,OPTICALSIGN TEXT);"+"\n")
    for i in range (0,39):
        f.write('INSERT INTO FILTRO_TRANSPARENTES_EN VALUES("'+str(int(datos[i][0]))+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'","'+str(datos[i][9])+'","'+str(datos[i][10])+'","'+str(datos[i][11])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('filtro_transparentes_es.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('filtro_transparentes_es.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE FILTRO_TRANSPARENTES_ES;"+"\n")
    f.write("CREATE TABLE FILTRO_TRANSPARENTES_ES(ID TEXT,MINERAL TEXT,RELIEF TEXT,COLOR TEXT,PLEOCHROISM TEXT,NUMBERCLEAVAGEDIRECTIONS TEXT,ANGLEOFCLEAVAGE TEXT,INTERFERENCECOLOR TEXT,EXTINCTION TEXT,TWINNING TEXT,INTERFERENCEFIGURE TEXT,OPTICALSIGN TEXT);"+"\n")
    for i in range (0,39):
        f.write('INSERT INTO FILTRO_TRANSPARENTES_ES VALUES("'+str(int(datos[i][0]))+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'","'+str(datos[i][9])+'","'+str(datos[i][10])+'","'+str(datos[i][11])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('filtro_transparentes_ca.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('filtro_transparentes_ca.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE FILTRO_TRANSPARENTES_CA;"+"\n")
    f.write("CREATE TABLE FILTRO_TRANSPARENTES_CA(ID TEXT,MINERAL TEXT,RELIEF TEXT,COLOR TEXT,PLEOCHROISM TEXT,NUMBERCLEAVAGEDIRECTIONS TEXT,ANGLEOFCLEAVAGE TEXT,INTERFERENCECOLOR TEXT,EXTINCTION TEXT,TWINNING TEXT,INTERFERENCEFIGURE TEXT,OPTICALSIGN TEXT);"+"\n")
    for i in range (0,39):
        f.write('INSERT INTO FILTRO_TRANSPARENTES_CA VALUES("'+str(int(datos[i][0]))+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'","'+str(datos[i][8])+'","'+str(datos[i][9])+'","'+str(datos[i][10])+'","'+str(datos[i][11])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('propiedades_filtro_transparentes_en.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('propiedades_filtro_transparentes_en.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE PROPIEDADES_FILTRO_TRANSPARENTES_EN;"+"\n")
    f.write("CREATE TABLE PROPIEDADES_FILTRO_TRANSPARENTES_EN(RELIEF TEXT,COLOUR TEXT,PLEOCHROISM TEXT,NUMBEROFCLEAVAGEDIRECTIONS TEXT,ANGLEOFCLEAVAGE TEXT,INTERFERENCECOLOR TEXT,EXTINCTION TEXT,TWINNING TEXT);"+"\n")
    for i in range (0,1):
        f.write('INSERT INTO PROPIEDADES_FILTRO_TRANSPARENTES_EN VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('propiedades_filtro_transparentes_es.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('propiedades_filtro_transparentes_es.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE PROPIEDADES_FILTRO_TRANSPARENTES_ES;"+"\n")
    f.write("CREATE TABLE PROPIEDADES_FILTRO_TRANSPARENTES_ES(RELIEF TEXT,COLOUR TEXT,PLEOCHROISM TEXT,NUMBEROFCLEAVAGEDIRECTIONS TEXT,ANGLEOFCLEAVAGE TEXT,INTERFERENCECOLOR TEXT,EXTINCTION TEXT,TWINNING TEXT);"+"\n")
    for i in range (0,1):
        f.write('INSERT INTO PROPIEDADES_FILTRO_TRANSPARENTES_ES VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('propiedades_filtro_transparentes_ca.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('propiedades_filtro_transparentes_ca.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE PROPIEDADES_FILTRO_TRANSPARENTES_CA;"+"\n")
    f.write("CREATE TABLE PROPIEDADES_FILTRO_TRANSPARENTES_CA(RELIEF TEXT,COLOUR TEXT,PLEOCHROISM TEXT,NUMBEROFCLEAVAGEDIRECTIONS TEXT,ANGLEOFCLEAVAGE TEXT,INTERFERENCECOLOR TEXT,EXTINCTION TEXT,TWINNING TEXT);"+"\n")
    for i in range (0,1):
        f.write('INSERT INTO PROPIEDADES_FILTRO_TRANSPARENTES_CA VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'","'+str(datos[i][7])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('propiedades_filtro_opacos_en.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('propiedades_filtro_opacos_en.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE PROPIEDADES_FILTRO_OPACOS_EN;"+"\n")
    f.write("CREATE TABLE PROPIEDADES_FILTRO_OPACOS_EN(COLOR TEXT,REFLECTANCE TEXT,PLEOCHROISM TEXT,POLISHINGHARDNESS TEXT,ANISOTROPISM TEXT,INTERFERENCECOLORS TEXT,INTERNALREFLECTIONS TEXT);"+"\n")
    for i in range (0,1):
        f.write('INSERT INTO PROPIEDADES_FILTRO_OPACOS_EN VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('propiedades_filtro_opacos_es.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('propiedades_filtro_opacos_es.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE PROPIEDADES_FILTRO_OPACOS_ES;"+"\n")
    f.write("CREATE TABLE PROPIEDADES_FILTRO_OPACOS_ES(COLOR TEXT,REFLECTANCE TEXT,PLEOCHROISM TEXT,POLISHINGHARDNESS TEXT,ANISOTROPISM TEXT,INTERFERENCECOLORS TEXT,INTERNALREFLECTIONS TEXT);"+"\n")
    for i in range (0,1):
        f.write('INSERT INTO PROPIEDADES_FILTRO_OPACOS_ES VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'");'+"\n")

# read by default 1st sheet of an excel file
dataframe = pd.read_excel('propiedades_filtro_opacos_ca.xls')
datos = list(dataframe.itertuples(index=False, name=None))

with open('propiedades_filtro_opacos_ca.txt', 'w', encoding="utf-8") as f:
    f.write("DROP TABLE PROPIEDADES_FILTRO_OPACOS_CA;"+"\n")
    f.write("CREATE TABLE PROPIEDADES_FILTRO_OPACOS_CA(COLOR TEXT,REFLECTANCE TEXT,PLEOCHROISM TEXT,POLISHINGHARDNESS TEXT,ANISOTROPISM TEXT,INTERFERENCECOLORS TEXT,INTERNALREFLECTIONS TEXT);"+"\n")
    for i in range (0,1):
        f.write('INSERT INTO PROPIEDADES_FILTRO_OPACOS_CA VALUES("'+str(datos[i][0])+'","'+str(datos[i][1])+'","'+str(datos[i][2])+'","'+str(datos[i][3])+'","'+str(datos[i][4])+'","'+str(datos[i][5])+'","'+str(datos[i][6])+'");'+"\n")
