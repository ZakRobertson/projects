DELETE FROM track_info
INSERT INTO dbo.track_info (trackName, albumName, artistName, minutesPlayed, timesPlayed, timesSkipped)
SELECT trackName, albumName, artistName, SUM(msPlayed)/60000 AS totalMinutes, count(*), SUM(skipped)
FROM streams
GROUP BY trackName, albumName, artistName;