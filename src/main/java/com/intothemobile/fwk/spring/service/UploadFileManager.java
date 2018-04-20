package com.intothemobile.fwk.spring.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class UploadFileManager {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileManager.class);
	
	public static final int SAVE_TO_INTERNAL = 1;
	public static final int SAVE_TO_SFTP = 2;
	
	@Value("#{configProperties['server.upload.save.destination']}")
	private String destination;
	
	public UploadFileManager() {}
	public UploadFileManager(int destinationType) {
		switch (destinationType) {
		case SAVE_TO_INTERNAL:
			this.destination = "internal";
			break;
		case SAVE_TO_SFTP:
			this.destination = "sftp";
			break;
		default :
			throw new IllegalArgumentException("Unsupported destination type.");
		}
	}
	
	/*
	 * Internal disk configurations
	 */
	@Value("#{configProperties['server.upload.root.path']}")
	private String rootPath;

	/*
	 * SFTP configurations
	 */
	@Value("#{configProperties['server.upload.sftp.user']}")
	private String sftpUser;
	@Value("#{configProperties['server.upload.sftp.password']}")
	private String sftpPassword;
	@Value("#{configProperties['server.upload.sftp.address']}")
	private String sftpAddress;
	@Value("#{configProperties['server.upload.sftp.root']}")
	private String sftpRoot;

	/*
	 * About download
	 */
	@Value("#{configProperties['server.download.url.prefix']}")
	private String downloadUrlPrefix;
	
	public Map<String, String> saveFile (MultipartFile mFile) throws RuntimeException, IOException {
		return saveFile(mFile, "");
	}

	public Map<String, String> saveFile (MultipartFile mFile, String prefix) throws RuntimeException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		String returnUrl;
		
		long time = System.currentTimeMillis();
		String destinationDirName = makeUploadPath(time, prefix);
		String destinationFileName = (prefix != null ? prefix + "_" : "") + time + "_" + mFile.getOriginalFilename();
		
		if (logger.isDebugEnabled()) {
			logger.debug(destinationDirName + "/" + destinationFileName);
		}
		
		// Create destination directory
		File dir = new File (this.rootPath + "/" + destinationDirName);
		if (!(dir.exists())) {
			dir.mkdirs();
		}
		
		// Copy to destination directory
		String filePath = dir.getPath() + "/" + destinationFileName;
		File f = new File(filePath);
		
		mFile.transferTo(f);
		
		returnUrl = destinationDirName + "/" + destinationFileName;
		
		if ("sftp".equals(this.destination)) {
			sendSftp(f.getAbsolutePath(), this.sftpRoot + "/" + destinationDirName + "/" + destinationFileName);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("Download Url : " + this.downloadUrlPrefix + returnUrl);
		}
		
		map.put("fileUrl", this.downloadUrlPrefix + returnUrl);
		map.put("savedFileName", returnUrl);
		
		return map;
	}
	
	public void removeFile(String fileName) {
		File file = new File (this.rootPath + fileName);
		file.delete();
	}
	
	protected String makeUploadPath(long time) {
		return makeUploadPath(time, "");
	}
	
	protected String makeUploadPath(long time, String prefix) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH", Locale.KOREAN);
		return String.format("/%s/%s/%d", new Object[] { prefix, sdf.format(Long.valueOf(time)), Long.valueOf(time) });
	}

	protected boolean sendSftp(String localfile, String remotefile) {
		JSch jsch = new JSch();
		Session session = null;
		ChannelSftp sftpChannel;
		try {
			session = jsch.getSession(this.sftpUser, this.sftpAddress, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(this.sftpPassword);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp) channel;

			String[] pathArray = remotefile.split("/");
			
			if (logger.isDebugEnabled()) {
				logger.debug("localfile = " + localfile);
				logger.debug("remotefile = " + remotefile);
				logger.debug("pathArray Length = " + Integer.valueOf(pathArray.length));
			}
			
			sftpChannel.cd("/");
			for (int i = 1; i < pathArray.length - 1; ++i) {
				try {
					sftpChannel.mkdir(pathArray[i]);
					sftpChannel.cd(pathArray[i]);
				} catch (Exception e) {
					sftpChannel.cd(pathArray[i]);
				}
			}
			sftpChannel.put(localfile, remotefile);
			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			return false;
		} catch (SftpException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			return false;
		} finally {
			if ((session != null) && (session.isConnected())) {
				session.disconnect();
			}
		}

		return true;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getDownloadUrlPrefix() {
		return downloadUrlPrefix;
	}

	public void setDownloadUrlPrefix(String downloadUrlPrefix) {
		this.downloadUrlPrefix = downloadUrlPrefix;
	}
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSftpUser() {
		return sftpUser;
	}

	public void setSftpUser(String sftpUser) {
		this.sftpUser = sftpUser;
	}

	public String getSftpPassword() {
		return sftpPassword;
	}

	public void setSftpPassword(String sftpPassword) {
		this.sftpPassword = sftpPassword;
	}

	public String getSftpAddress() {
		return sftpAddress;
	}

	public void setSftpAddress(String sftpAddress) {
		this.sftpAddress = sftpAddress;
	}

	public String getSftpRoot() {
		return sftpRoot;
	}

	public void setSftpRoot(String sftpRoot) {
		this.sftpRoot = sftpRoot;
	}

}
