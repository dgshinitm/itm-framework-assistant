package com.intothemobile.fwk.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intothemobile.fwk.ancestors.YoutubeVideo;

public class YoutubeService {
	private static final Logger logger = LoggerFactory.getLogger(YoutubeService.class);
    
    private String youtubeAuthorId;
    
	public YoutubeVideo getVideo (String fileId) throws Exception {
		YoutubeVideo youtubeVideo = null;
		YoutubeEntry yEntry = getYoutubeEntry(fileId);
		
		Entry entry = yEntry.getEntry();
		
		if (entry != null) {
			youtubeVideo = new YoutubeVideo();
			youtubeVideo.setTitle(entry.getVideoTitle());
			youtubeVideo.setVideoContent(entry.getVideoContent());
			youtubeVideo.setThumbnail(entry.getVideoThumbnailUrl());
			youtubeVideo.setMediaContent(entry.getMediaContentUrl());
			youtubeVideo.setDateString(entry.getVideoPublishedDate());
			youtubeVideo.setRate(entry.getViewCount());
		}
		return youtubeVideo;
	}
	
	public List<YoutubeVideo> getVideoList(String author) throws Exception {
		List<YoutubeVideo> youtubeVideoList = null;
		YoutubeFeed feed = getYoutubeFeed(author);
		
		if (feed != null) {
			List<Entry> entryList = feed.getFeed().getEntry();
			youtubeVideoList = new ArrayList<YoutubeVideo> ();
			
			for (Entry entry: entryList) {
				YoutubeVideo youtubeVideo = new YoutubeVideo();
				youtubeVideo.setTitle(entry.getVideoTitle());
				youtubeVideo.setVideoContent(entry.getVideoContent());
				youtubeVideo.setThumbnail(entry.getVideoThumbnailUrl());
				youtubeVideo.setMediaContent(entry.getMediaContentUrl());
				youtubeVideo.setDateString(entry.getVideoPublishedDate());
				youtubeVideo.setRate(entry.getViewCount());
				
				youtubeVideoList.add(youtubeVideo);
			}
		}
		
		return youtubeVideoList;
	}
    
	/**
	 * 유튜브 동영상 정보를 가져온다.
	 * 
	 * @param fileId
	 *            유튜브동영상 ID
	 * @return
	 * @throws Exception
	 */
	private YoutubeEntry getYoutubeEntry(String fileId) throws Exception {
		String url = "http://gdata.youtube.com/feeds/api/videos/" + fileId + "?alt=json";
		
		logger.debug(url);
		
		return null; //HttpJsonClient.getInstance(HttpContentType.JSON).sendRequest(HttpMethod.GET, url, null, null, YoutubeEntry.class);
	}

	/**
	 * 특정 유튜브 계정으로 올려진 유튜브 동영상 리스트를 가져온다
	 * 
	 * @param author
	 * 			유튜브계정
	 * @return
	 * @throws Exception
	 */
	private YoutubeFeed getYoutubeFeed(String author) throws Exception {
		if (author == null || "".equals("")) {
			author = youtubeAuthorId;
		}

		String url = "http://gdata.youtube.com/feeds/api/videos?max-results=50&alt=json&author=" + author;
		
		logger.debug(url);
		
		return null; //HttpJsonClient.getInstance(HttpContentType.JSON).sendRequest(HttpMethod.GET, url, null, null, YoutubeFeed.class);
	}
}

class YoutubeFeed {
	private Feed feed;
	
	public Feed getFeed() {
		return feed;
	}
	
	public void setFeed(Feed feed) {
		this.feed = feed;
	}
}

class YoutubeEntry {
	private Entry entry;

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}
}

class Feed {
	private List<Entry> entry;

	public List<Entry> getEntry() {
		return entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}
}

class Entry {
	private Id id;
	private Published published;
	private Title title;
	private Content content;
	private MediaGroup media$group;
	private Statistics yt$statistics;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Published getPublished() {
		return published;
	}

	public void setPublished(Published published) {
		this.published = published;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public MediaGroup getMedia$group() {
		return media$group;
	}

	public void setMedia$group(MediaGroup media$group) {
		this.media$group = media$group;
	}

	public Statistics getYt$statistics() {
		return yt$statistics;
	}

	public void setYt$statistics(Statistics yt$statistics) {
		this.yt$statistics = yt$statistics;
	}

	// -- 호출 Method
	public String getVideoId() {
		if (id != null) {
			return id.getId();
		}
		return null;
	}

	public String getVideoPublishedDate() {
		if (published != null) {
			return published.getPublishedDate();
		}
		return null;
	}

	public String getVideoTitle() {
		if (title != null) {
			return title.getTitle();
		}
		return null;
	}

	public String getVideoContent() {
		if (content != null) {
			return content.getContent();
		}
		return null;
	}

	public String getVideoThumbnailUrl() {
		if (media$group != null) {
			return media$group.getThurmbnailUrl();
		}
		return null;
	}

	public String getMediaContentUrl() {
		if (media$group != null) {
			return media$group.getMediaContentUrl();
		}
		return null;
	}

	public String getViewCount() {
		if (yt$statistics != null) {
			return yt$statistics.getViewCount();
		}
		return null;
	}

	public String toString() {
		return "id=" + getVideoId() + ", publishedDate="
				+ getVideoPublishedDate() + ", title=" + getVideoTitle()
				+ ", content=" + getVideoContent() + ", thumbnailUrl="
				+ getVideoThumbnailUrl() + ", viewCount=" + getViewCount();
	}
}

class Id {
	private String $t;

	public String get$t() {
		return $t;
	}

	public void set$t(String $t) {
		this.$t = $t;
	}

	public String getId() {
		return $t.substring(42, $t.length());
	}
}

class Published {
	private String $t;

	public String get$t() {
		return $t;
	}

	public void set$t(String $t) {
		this.$t = $t;
	}

	public String getPublishedDate() {
		return $t;
	}
}

class Title {
	private String $t;

	public String get$t() {
		return $t;
	}

	public void set$t(String $t) {
		this.$t = $t;
	}

	public String getTitle() {
		return get$t();
	}
}

class Content {
	private String $t;

	public String get$t() {
		return $t;
	}

	public void set$t(String $t) {
		this.$t = $t;
	}

	public String getContent() {
		return get$t();
	}
}

class MediaGroup {
	private List<MediaThumbnail> media$thumbnail;
	private List<MediaContent> media$content;

	public List<MediaThumbnail> getMedia$thumbnail() {
		return media$thumbnail;
	}

	public void setMedia$thumbnail(List<MediaThumbnail> media$thumbnail) {
		this.media$thumbnail = media$thumbnail;
	}

	public List<MediaContent> getMedia$content() {
		return media$content;
	}

	public void setMedia$content(List<MediaContent> media$content) {
		this.media$content = media$content;
	}

	public String getThurmbnailUrl() {
		if (media$thumbnail != null && media$thumbnail.size() > 0) {
			MediaThumbnail mediaThumbnail = media$thumbnail.get(0);
			return mediaThumbnail.getUrl();
		} else {
			return null;
		}
	}
	
	public String getMediaContentUrl() {
		if (media$content != null && media$content.size() > 0) {
			MediaContent mediaContent = media$content.get(0);
			return mediaContent.getUrl();
		} else {
			return null;
		}
	}
}

class MediaContent {
	private String url;
	private String type;
	private String medium;
	private boolean isDefault;
	private String expression;
	private int duration;
	private int yt$format;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getYt$format() {
		return yt$format;
	}
	public void setYt$format(int yt$format) {
		this.yt$format = yt$format;
	}
}

class MediaThumbnail {
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

class Statistics {
	private String viewCount;

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}
}
