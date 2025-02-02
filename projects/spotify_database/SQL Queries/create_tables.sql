CREATE TABLE raw_data(
	endTime DATETIME,
	device VARCHAR(100),
	msPlayed INT,
	country VARCHAR(10),
	ipAddress VARCHAR(100),

	trackName NVARCHAR(255),
	artistName NVARCHAR(255),
	albumName NVARCHAR(255),
	trackURI VARCHAR(100),

	episodeName VARCHAR(100),
	podcastName VARCHAR(100),
	episodeURI VARCHAR(100),

	audiobookTitle VARCHAR(100),
	audiobookURI VARCHAR(100),
	audiobookChapterURI VARCHAR(100),
	audiobookChapterTitle VARCHAR(100),

	reasonStart VARCHAR(100),
	reasonEnd VARCHAR(100),
	shuffle VARCHAR(10),
	skipped VARCHAR(10),
	offlineStatus VARCHAR(10),
	offlineTimestamp VARCHAR(100),
	incognitoMode VARCHAR(10));

CREATE TABLE streams (
	streamID INT identity(1,1) Primary key,
	trackName NVARCHAR(255),
	albumName NVARCHAR(255),
	artistName NVARCHAR(255),
	trackURI VARCHAR(100),
	msPlayed INT,
	endTime DATETIME,
	skipped INT);

CREATE TABLE track_info (
	trackID INT identity(1,1) Primary key,
	trackName NVARCHAR(255),
	albumName NVARCHAR(255),
	artistName NVARCHAR(255),
	timesPlayed INT,
	minutesPlayed INT,
	timesSkipped INT);
