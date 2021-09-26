package my.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.model.dao.MyDao;
import qna.model.vo.Board;
import qna.model.vo.PageInfo;
import shop.model.vo.Charge;
import shop.model.vo.Purchase;
import shop.model.vo.Refund;
import study.model.vo.Study;

public class MyService {
	private MyDao md = new MyDao();

	// 내가 작성한 게시글 가져오기
	public Map<String, Object> selectMyBoardList(int page, int userNo) {
		Connection conn = getConnection();
		
		int listCount = md.getListCount(conn, userNo);
	
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		
		List<Board> MyboardList = md.selectMyBoardList(conn, pi, userNo);
		
		Map<String, Object> returnMap = new HashMap<>();
		 
		returnMap.put("pi", pi);
		returnMap.put("MyboardList", MyboardList);
		 
		return returnMap;
	}
	
	// 댓글을 단 게시글 리스트
	public Map<String, Object> selectMyReplyList(int page, int userNo) {
		Connection conn = getConnection();
		
		int ReplyListCount = md.getReplyCount(conn, userNo);
	
		PageInfo pi2 = new PageInfo(page, ReplyListCount, 10, 10);
		
		List<Board> MyReplyList = md.selectMyReplyList(conn, pi2, userNo);
		
		Map<String, Object> returnMap = new HashMap<>();
		 
		returnMap.put("pi2", pi2);
		returnMap.put("MyReplyList", MyReplyList);
		 
		return returnMap;
	}

	// 개설한 스터디 리스트 가져오기
	public Map<String, Object> selectMyOpenStudyList(int page, int userNo) {
		Connection conn = getConnection();
		
		int openStudyCount = md.getOpenStudyCount(conn, userNo);
	
		PageInfo pi = new PageInfo(page, openStudyCount, 10, 10);
		
		List<Study> MyOpenStudyList = md.selectMyOpenStudyList(conn, pi, userNo);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("pi", pi);
		returnMap.put("MyOpenStudyList", MyOpenStudyList);
		 
		return returnMap;
	}

	// 참여한 스터디 리스트 가져오기
	public Map<String, Object> selectMyJoinStudyList(int userNo) {
		Connection conn = getConnection();
		
		List<Study> MyJoinStudyList = md.selectMyJoinStudyList(conn, userNo);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("MyJoinStudyList", MyJoinStudyList);
		 
		return returnMap;
	}

	// 개설한 스터디 삭제
	public int deleteOpenStudy(int deleteSNo, int userNo) {
		Connection conn = getConnection();
		
		int result = md.deleteOpenStudy(conn, deleteSNo, userNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 참여한 스터디 나가기
	public int exitJoinStudy(int exitSNo, int userNo) {
		Connection conn = getConnection();
		
		int result = md.exitJoinStudy(conn, exitSNo, userNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 구매한 아이템 리스트 가져오기
	public Map<String, Object> selectItemList(int userNo) {
		Connection conn = getConnection();
		
		List<Purchase> ItemList = md.selectItemList(conn, userNo);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("ItemList", ItemList);
		 
		return returnMap;
	}

	// 환불 신청
	public int insertRefundCoin(Refund r) {
		Connection conn = getConnection();
		
		int result = md.insertRefundCoin(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);

		return result;
	}

	public Map<String, Object> selectPurchaseList(int page, int userNo) {
		Connection conn = getConnection();
		
		int RefundCount = md.getPurchaseCount(conn, userNo);
		
		PageInfo pi = new PageInfo(page, RefundCount, 10, 10);
		
		List<Purchase> PurchaseList = md.selectPurchaseList(conn, pi, userNo);
	
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("PurchaseList", PurchaseList);
		 
		return returnMap;
	}

	public Map<String, Object> selectChargeList(int page, int userNo) {
		Connection conn = getConnection();
		
		int RefundCount = md.getChargeCount(conn, userNo);
		
		PageInfo pi = new PageInfo(page, RefundCount, 10, 10);
		
		List<Charge> ChargeList = md.selectChargeList(conn, pi, userNo);
	
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("ChargeList", ChargeList);
		 
		return returnMap;
	}

	public Map<String, Object> selectRefundList(int page, int userNo) {
		Connection conn = getConnection();
		
		int RefundCount = md.getRefundCount(conn, userNo);
		
		PageInfo pi = new PageInfo(page, RefundCount, 10, 10);
		
		List<Refund> RefundList = md.selectUserRefundList(conn, pi, userNo);
	
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("RefundList", RefundList);
		 
		return returnMap;
	}

}