目前地形測試檔為 JAVA_N23.txt
JAVA_N23.txt 是由DTED中E120的N23.dt1所轉換出
N23.dt1 透過 QGIS 轉出 N23.asc
N23.asc 經由 r.refine(https://github.com/jontodd/r.refine)進行三角化得出 N23.tin
JAVA_N23.txt 為 N23.tin之文字檔

目前路徑測試檔為 path.txt
每一行代表.asc中的網格位置
每兩行為路徑分別表示起終點位置

在main中呼叫佈署基地台的方法需帶入兩個參數
Deploy_BS_on_Terrain DBT= new Deploy_BS_on_Terrain();
DBT.Deploy("JAVA_N23.txt", "path.txt");
參數分別為地形檔之檔案路徑以及路徑檔之檔案路徑
