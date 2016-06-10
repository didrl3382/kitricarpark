package com.carpark.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.carpark.common.model.ParkingDetailDto;
import com.carpark.common.model.ZipDto;
import com.carpark.db.DBClose;
import com.carpark.db.DBConnection;
import com.carpark.member.model.ReportDto;

public class MemberParkingDaoImpl implements MemberParkingDao {
	
	private static MemberParkingDao memberParkingDao;
	
	static {
		memberParkingDao = new MemberParkingDaoImpl();
	}
	
	private MemberParkingDaoImpl(){}
	
	public static MemberParkingDao getMemberParkingDao() {
		return memberParkingDao;
	}

	@Override
	public void parkingRegister(ParkingDetailDto parkingDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.makeConnection();
			String sql = "";
			sql += "insert all \n";
			sql += "	into parking (park_id, park_name, park_capacity, owner_id, latitude, "
										+ "longitude, emd_code, content, detail_addr, park_public) \n";
			sql += "	values (? ,? ,? ,? ,? ,?, ?, ?, ?, ?) \n";
//			sql += "	into parking_facility (park_id, facility, feature) \n";
//			sql += "	values (?, ?, ?) \n";
//			sql += "	into parking_img (park_id, file_name, file_path) \n";
//			sql += "	values (?, ?, ?) \n";
			sql += "	into parking_detail (park_id, park_flag, PAY_YN, satur_pay_yn, holi_pay_yn, "
												+ "fulltime_monthly_pay, park_rate, "
												+ "park_time_rate, add_park_rate, day_max_pay, park_visit) \n";
			sql += "	values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			sql += "select * from dual";
			
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			//parking table
			pstmt.setInt(++idx, parkingDto.getPark_id());
			pstmt.setString(++idx, parkingDto.getPark_name());
			pstmt.setInt(++idx, parkingDto.getPark_capacity());
			pstmt.setString(++idx, parkingDto.getOwner_id());
			pstmt.setDouble(++idx, parkingDto.getLatitude());
			pstmt.setDouble(++idx, parkingDto.getLongitude());
			pstmt.setInt(++idx, parkingDto.getEmd_code());
			pstmt.setString(++idx, parkingDto.getContent());
			pstmt.setString(++idx, parkingDto.getDetailAddr());
			pstmt.setInt(++idx, parkingDto.getPark_public());
			//parking_facility table
//			pstmt.setInt(++idx, parkingDto.getPark_id());
//			pstmt.setString(++idx, parkingDto.getFacility());
//			pstmt.setString(++idx, parkingDto.getFeature());
			//parking_img table
//			pstmt.setInt(++idx, parkingDto.getPark_id());
//			pstmt.setString(++idx, parkingDto.getImg_file_name());
//			pstmt.setString(++idx, parkingDto.getImg_file_path());
			//parking_detail table
			pstmt.setInt(++idx, parkingDto.getPark_id());
			pstmt.setInt(++idx, parkingDto.getPark_flag());
			pstmt.setString(++idx, parkingDto.getPay_yn());
			pstmt.setString(++idx, parkingDto.getSatur_pay_yn());
			pstmt.setString(++idx, parkingDto.getHoli_pay_yn());
			pstmt.setInt(++idx, parkingDto.getFulltime_monthly_pay());
			pstmt.setInt(++idx, parkingDto.getPark_rate());
			pstmt.setInt(++idx, parkingDto.getPark_time_rate());
			pstmt.setInt(++idx, parkingDto.getAdd_park_rate());
			pstmt.setInt(++idx, parkingDto.getDay_max_pay());
			pstmt.setInt(++idx, parkingDto.getVisit());

			pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

	}

	@Override
	public void parkingModify(ParkingDetailDto parkingDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);
			String sql = "";
			sql += "update parking \n";
			sql += "set park_name = ?, park_capacity = ?, latitude = ?, \n";
			sql += "longitude = ?, detail_addr = ?, content = ?, park_public \n";
			sql += "where park_id = ? \n";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, parkingDto.getPark_name());
			pstmt.setInt(2, parkingDto.getPark_capacity());
			pstmt.setDouble(3, parkingDto.getLatitude());
			pstmt.setDouble(4, parkingDto.getLongitude());
			pstmt.setString(6, parkingDto.getDetailAddr());
			pstmt.setString(7, parkingDto.getContent());
			pstmt.setInt(8, parkingDto.getPark_public());
			pstmt.setInt(9, parkingDto.getPark_id());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			
//			sql = "update parking_facility \n";
//			sql += "set facility = ?, feature = ? \n";
//			sql += "where park_id = ? \n";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, parkingDto.getFacility());
//			pstmt.setString(2, parkingDto.getFeature());
//			pstmt.setInt(3, parkingDto.getPark_id());
//			pstmt.executeUpdate();
//			conn.commit();
//			pstmt.close();
			
//			sql = "update parking_img \n";
//			sql += "set file_name = ?, file_path = ? \n";
//			sql += "where park_id = ? \n";
//			System.out.println(sql);
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, parkingDto.getImg_file_name());
//			pstmt.setString(2, parkingDto.getImg_file_path());
//			pstmt.setInt(3, parkingDto.getPark_id());
//			pstmt.executeUpdate();
//			conn.commit();
//			pstmt.close();
			
			sql = "update parking_detail \n";
			sql += "set park_flag = ?, PAY_YN = ?, satur_pay_yn = ?, holi_pay_yn = ?, \n";
			sql += "fulltime_monthly_pay = ?, park_rate = ?, \n";
			sql += "park_time_rate = ?, add_park_rate = ?, day_max_pay = ? \n";
			sql += "where park_id = ? \n";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parkingDto.getPark_flag());
			pstmt.setString(2, parkingDto.getPay_yn());
			pstmt.setString(3, parkingDto.getSatur_pay_yn());
			pstmt.setString(4, parkingDto.getHoli_pay_yn());
			pstmt.setInt(5, parkingDto.getFulltime_monthly_pay());
			pstmt.setInt(6, parkingDto.getPark_rate());
			pstmt.setInt(7, parkingDto.getPark_time_rate());
			pstmt.setInt(8, parkingDto.getAdd_park_rate());
			pstmt.setInt(9, parkingDto.getDay_max_pay());
			pstmt.setInt(10, parkingDto.getPark_id());
			pstmt.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

	}

	@Override
	public List<ParkingDetailDto> parkingList(Map<String, String> map) {
		List<ParkingDetailDto> list = new ArrayList<ParkingDetailDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String ownerId = map.get("ownerId");
		String key = map.get("key");
		String word = map.get("word");
		
		try {
			conn = DBConnection.makeConnection();
			String sql = "";
			sql += "select b.rn, b.park_id, b.park_name, b.owner_id, b.park_type, b.park_flag, b.park_avgPoint \n";
			sql += "from \n";
		    sql += "  		(select rownum rn, a.park_id, a.park_name, a.owner_id, a.park_type, a.park_flag, a.park_avgPoint \n"; 
		    sql += "         from \n";
		    sql += "     		  (select p.park_id, park_name, owner_id, park_type, park_flag, park_avgPoint \n";
		    sql += "               from parking p, parking_detail d \n";
		    sql += "               where p.park_id = d.park_id \n";
		    if(ownerId != null && !ownerId.isEmpty()) {
		    	sql += "           and owner_id = ? \n";
		    }
		    if(key != null && !key.isEmpty()) {
		    	if(word != null && !word.isEmpty()) {
		    		if("park_name".equals(key))
		    			sql += "   and park_name like '%'||?||'%' \n";
		    		else
		    			sql += "   and " + key + " = ? \n";						
		    	}
		    }
		    sql += "               order by park_name) a \n";
		    sql += "         where rownum < ? ) b \n";
		    sql += "where rn > ? \n";
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			if(ownerId != null && !ownerId.isEmpty()) {
				pstmt.setString(++idx, ownerId);
			}
			if(key != null && !key.isEmpty()) {
				if(word != null && !word.isEmpty()) {
					pstmt.setString(++idx, map.get("word"));				
				}
			}
			pstmt.setString(++idx, map.get("end"));
			pstmt.setString(++idx, map.get("start"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ParkingDetailDto parkingDto = new ParkingDetailDto();
				parkingDto.setPark_id(rs.getInt("park_id"));
				parkingDto.setPark_name(rs.getString("park_name"));
				parkingDto.setOwner_id(rs.getString("owner_id"));
				parkingDto.setPark_type(rs.getString("park_type"));
				parkingDto.setPark_flag(rs.getInt("park_flag"));
				parkingDto.setPark_avgPoint(rs.getInt("park_avgPoint"));
				
				list.add(parkingDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	@Override
	public int parkingDelete(int parkId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);
			String sql = "";
			sql += "delete parking_Detail \n";
			sql += "where park_id = ? \n";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parkId);
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			
//			sql = "delete parking_img \n";
//			sql += "where park_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, parkId);
//			pstmt.executeUpdate();
//			conn.commit();
//			pstmt.close();
//			
//			sql = "delete parking_facility \n";
//			sql += "where park_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, parkId);
//			pstmt.executeUpdate();
//			conn.commit();
//			pstmt.close();
			
			sql = "delete parking \n";
			sql += "where park_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parkId);
			cnt = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		return cnt;
	}

	@Override
	public List<ZipDto> parkingSearch(Map<String, String> map) {
		List<ZipDto> list = new ArrayList<ZipDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String address = map.get("address");
		
		try {
			conn = DBConnection.makeConnection();
			String sql = "";			
			sql += "select b.rn, b.zip1, b.zip2, b.sido, b.gugun, b.dong, b.bunji \n";
			sql += "from \n";
			sql += "      (select rownum rn, a.zip1, a.zip2, a.sido, a.gugun, a.dong, a.bunji \n";
			sql += "      from \n";
			sql += "            (select substr(zipcode, 1, instr(zipcode, '-') -1) zip1, \n"; 
			sql += "                    substr(zipcode, instr(zipcode, '-') + 1, 3) zip2, \n"; 
			sql += "                    sido, gugun, dong, nvl(bunji, ' ') bunji \n"; 
			sql += "            from zipcode \n";
			sql += "            where dong like '%'||?||'%' \n";
			sql += "            order by sido) a \n";
			sql += "            where rownum < ? \n";
			sql += "      ) b \n";
			sql += "where rn > ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, address);
			pstmt.setString(2, map.get("end"));
			pstmt.setString(3, map.get("start"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipDto zipDto = new ZipDto();
				zipDto.setZip1(rs.getString("zip1"));
				zipDto.setZip2(rs.getString("zip2"));
				zipDto.setSido(rs.getString("sido"));
				zipDto.setGugun(rs.getString("gugun"));
				zipDto.setDong(rs.getString("dong"));
				zipDto.setBunji(rs.getString("bunji"));
				
				list.add(zipDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public ParkingDetailDto parkingView(int parkId) {
		ParkingDetailDto parkingDto = new ParkingDetailDto();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.makeConnection();
			String sql = "";
			sql += "select p.park_id, park_name, park_capacity, owner_id, latitude, \n";
			sql += "		longitude, content, detail_addr, \n";
			sql += "		park_flag, park_avgPoint, get_status, cur_parking, PAY_YN, satur_pay_yn, holi_pay_yn,\n";
			sql += "		fulltime_monthly_pay, park_rate, park_time_rate, add_park_rate, day_max_pay \n";
			sql += "from parking p, parking_detail d \n";
			sql += "where p.park_id = d.park_id \n";
			sql += "and p.park_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parkId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				parkingDto.setPark_id(rs.getInt("park_id"));
				parkingDto.setPark_name(rs.getString("park_name"));
				parkingDto.setPark_capacity(rs.getInt("park_capacity"));
				parkingDto.setOwner_id(rs.getString("owner_id"));
				parkingDto.setLatitude(rs.getDouble("latitude"));
				parkingDto.setLongitude(rs.getDouble("longitude"));
				parkingDto.setContent(rs.getString("content"));
				parkingDto.setPark_flag(rs.getInt("park_flag"));
				parkingDto.setPark_avgPoint(rs.getInt("park_avgPoint"));
				parkingDto.setGet_status(rs.getInt("get_status"));
				parkingDto.setCur_parking(rs.getInt("cur_parking"));
				parkingDto.setPay_yn(rs.getString("PAY_YN"));
				parkingDto.setSatur_pay_yn(rs.getString("satur_pay_yn"));
				parkingDto.setHoli_pay_yn(rs.getString("holi_pay_yn"));
				parkingDto.setFulltime_monthly_pay(rs.getInt("fulltime_monthly_pay"));
				parkingDto.setPark_rate(rs.getInt("park_rate"));
				parkingDto.setPark_time_rate(rs.getInt("park_time_rate"));
				parkingDto.setAdd_park_rate(rs.getInt("add_park_rate"));
				parkingDto.setDay_max_pay(rs.getInt("day_max_pay"));
				parkingDto.setDetailAddr(rs.getString("detail_addr"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return parkingDto;
	}

}
