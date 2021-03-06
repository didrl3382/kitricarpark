package com.carpark.member.model.service;

import com.carpark.util.PageNavigator;

public interface CommonService {

	int getNextSeq();
	PageNavigator getPageNavigator(String id, int bcode, int pg, String key, String word);
	PageNavigator getPageNavigatorAddress(String address, int pg);
	PageNavigator getPageNavigatorParking(String ownerId, int pg, String key, String word);
	PageNavigator getPageNavigatorAdminParking(int pg, String flag, String visit, String key, String word);
	PageNavigator getPageNavigatorUser(String userId, int bcode, int pg, String key, String word);
	PageNavigator getPageNavigatorReceiver(String receiveId, int pg, String key, String word);
	PageNavigator getPageNavigatorAdminReport(int bcode, int pg, String key, String word);
	PageNavigator getPageNavigatorAdminReportFlag(String flag, int bcode, int pg, String key, String word);
	int getNextParkingId();
	
}
