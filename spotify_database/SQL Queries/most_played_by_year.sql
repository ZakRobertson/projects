SELECT trackName, 
	artistName,
	albumName,
	YEAR(endTime) AS yearPlayed, 
	SUM(msPlayed) / 60000 AS minutesPlayed,
	SUM(skipped) AS timesSkipped
FROM streams
GROUP BY trackName, albumName, artistName, YEAR(endTime)
ORDER BY minutesPlayed DESC;

