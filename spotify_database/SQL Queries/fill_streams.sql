DELETE FROM streams 
INSERT INTO dbo.streams (trackName, albumName, artistName, trackURI, msPlayed, endTime, skipped)
SELECT trackName, albumName, artistName, trackURI, msPlayed, endTime, IIF (skipped LIKE 'TRUE', 1, 0)
FROM dbo.raw_data;

DELETE FROM streams
SELECT * 
WHERE trackURI IS NULL


