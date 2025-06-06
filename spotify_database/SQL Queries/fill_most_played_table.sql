DELETE FROM dbo.most_played_by_year;

INSERT INTO dbo.most_played_by_year (trackName, albumName, artistName, minutesPlayed, yearPlayed, timesSkipped)
SELECT trackName, 
	artistName,
	albumName,
	SUM(msPlayed) / 60000 AS minutesPlayed,
	YEAR(endTime) AS yearPlayed, 
	SUM(skipped) AS timesSkipped
FROM [spotify_data].[dbo].[streams]
GROUP BY trackName, albumName, artistName, YEAR(endTime);