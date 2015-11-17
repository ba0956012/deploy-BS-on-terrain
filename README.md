目前地形測試檔為 JAVA_N23.txt  
JAVA_N23.txt 是由DTED中E120的N23.dt1所轉換出  
N23.dt1 透過 QGIS 轉出 N23.asc   
N23.asc 經由 r.refine (https://github.com/jontodd/r.refine) 進行三角化得出 N23.tin    
JAVA_N23.txt 為 N23.tin之文字檔   
   
目前路徑測試檔為 path.txt   
每一行代表經緯度   
每兩行為路徑分別表示起終點位置   
    
在main中呼叫佈署基地台的方法需帶入8個參數   
Deploy_BS_on_Terrain DBT= new Deploy_BS_on_Terrain();   
	以N23.dt1為例:
	DBT.Deploy("JAVA_N23.txt", "path.txt" , 
	1200,
	1200,
	119.999583333333,//左下角x經度
	22.999583333333,//左下角x緯度
	0.000833333333, 
	100.0);    
參數為(地形檔之檔案路徑,路徑檔之檔案路徑,.asc檔的ncols,.asc檔的nrows,.asc檔的xllcorner,.asc檔的yllcorner,.asc檔的cellsize,基地台通訊半徑以公尺為單位)
