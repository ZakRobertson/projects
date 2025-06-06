DELETE FROM raw_data
BULK INSERT raw_data
FROM 'C:\Users\zakgr\projects\spotify_database\spotify_data\raw_data_prepped.csv'
WITH (FIRSTROW = 2,
	FIELDTERMINATOR = ',',
	ROWTERMINATOR = '\n');


