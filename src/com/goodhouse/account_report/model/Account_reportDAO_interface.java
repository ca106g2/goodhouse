package com.goodhouse.account_report.model;

import java.util.*;

public interface Account_reportDAO_interface {
		public void insert(Account_reportVO account_reportVO);
		public void update(Account_reportVO account_reportVO);
		public void delete(String acc_rep_id);
		public Account_reportVO findByPrimaryKey(String acc_rep_id);
		public List<Account_reportVO> getAll();
}
