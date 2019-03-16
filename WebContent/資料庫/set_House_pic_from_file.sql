--------------------------------PHOTO
--------------------------------------------------------
--  FUNCTION for Table PRODUCT BLOB
--------------------------------------------------------
/* 檔案儲存的目錄路徑 */ 
CREATE OR REPLACE  DIRECTORY MEDIA_DIR AS 'C:/photos1/'; 

/* 擷取檔案的FUNCTION */ 
CREATE OR REPLACE FUNCTION load_blob( myFileName VARCHAR) RETURN BLOB as result BLOB;  
  myBFILE      BFILE;
  myBLOB       BLOB;
BEGIN
    myBFILE := BFILENAME('MEDIA_DIR',myFileName);
    dbms_lob.createtemporary(myBLOB, TRUE);
    dbms_lob.fileopen(myBFILE,dbms_lob.file_readonly);
    dbms_lob.loadfromfile(myBLOB,myBFILE,dbms_lob.getlength(myBFILE) );
    dbms_lob.fileclose(myBFILE);
    RETURN myBLOB;
END load_blob;

/* 分開執行*/
/* 積分商城圖片 */ 
CREATE OR REPLACE  DIRECTORY PG_DIR AS 'C:/pointgoods_pic/'; 

/* 積分商城圖片擷取檔案的FUNCTION */ 
CREATE OR REPLACE FUNCTION load_pg( myFileName VARCHAR) RETURN BLOB as result BLOB;  
  myBFILE      BFILE;
  myBLOB       BLOB;
BEGIN
    myBFILE := BFILENAME('PG_DIR',myFileName);
    dbms_lob.createtemporary(myBLOB, TRUE);
    dbms_lob.fileopen(myBFILE,dbms_lob.file_readonly);
    dbms_lob.loadfromfile(myBLOB,myBFILE,dbms_lob.getlength(myBFILE) );
    dbms_lob.fileclose(myBFILE);
    RETURN myBLOB;
END load_pg;

/* 良民證圖片 */ 
CREATE OR REPLACE  DIRECTORY LAN_DIR AS 'C:/lan_pic/';
/* 良民證圖片擷取檔案的FUNCTION */ 
CREATE OR REPLACE FUNCTION load_lan( myFileName VARCHAR) RETURN BLOB as result BLOB;  
  myBFILE      BFILE;
  myBLOB       BLOB;
BEGIN
    myBFILE := BFILENAME('LAN_DIR',myFileName);
    dbms_lob.createtemporary(myBLOB, TRUE);
    dbms_lob.fileopen(myBFILE,dbms_lob.file_readonly);
    dbms_lob.loadfromfile(myBLOB,myBFILE,dbms_lob.getlength(myBFILE) );
    dbms_lob.fileclose(myBFILE);
    RETURN myBLOB;
END load_lan;
