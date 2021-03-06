package com.carpark.admin.parking.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.action.Action;
import com.carpark.common.model.ZipDto;
import com.carpark.member.model.service.CommonServiceImpl;
import com.carpark.member.model.service.MemberParkingServiceImpl;
import com.carpark.util.Encoder;
import com.carpark.util.NumberCheck;
import com.carpark.util.PageNavigator;
import com.carpark.util.StringCheck;

public class AdminParkingSearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		
		String parkAddress = Encoder.isoToUtf(StringCheck.nullToBlank(request.getParameter("parkAddress")));

		HttpSession session = request.getSession();
		session.setAttribute("address", parkAddress);
		List<ZipDto> list = MemberParkingServiceImpl.getMemberParkingservice().parkingSearch(parkAddress, pg);
		request.setAttribute("addressList", list);

		PageNavigator navigator = CommonServiceImpl.getCommonService().getPageNavigatorAddress(parkAddress, pg);
		navigator.setRoot(request.getContextPath());
		navigator.setNavigator("adminSearchAddress");
		request.setAttribute("navigator", navigator);

		return "/admin/parking/parksearch.jsp";
	}

}
