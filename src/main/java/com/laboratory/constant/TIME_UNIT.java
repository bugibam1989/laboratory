package com.laboratory.constant;

public enum TIME_UNIT {
	DAY(4, "요일 ", 7) {
		@Override
		public String[] getUnitList() {
			String[] list = { "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };
			return list;
		}
	},
	MONTH(3, "월 ", 12) {
		@Override
		public String[] getUnitList() {
			String[] rst = new String[this.getSize() + 1];
			for (int i = 0; i < size + 1; i++) {
				rst[i] = i + this.getKorSuffix();
			}
			return rst;
		}
	},
	DATE(2, "일 ", 31) {
		@Override
		public String[] getUnitList() {
			String[] rst = new String[this.getSize() + 1];
			for (int i = 0; i < size + 1; i++) {
				rst[i] = i + this.getKorSuffix();
			}
			return rst;
		}
	},
	HOUR(1, "시 ", 24) {
		@Override
		public String[] getUnitList() {
			String[] rst = new String[this.getSize() + 1];
			for (int i = 0; i < size + 1; i++) {
				rst[i] = i + this.getKorSuffix();
			}
			return rst;
		}
	},
	MIN(0, "분 ", 60) {
		@Override
		public String[] getUnitList() {
			String[] rst = new String[this.getSize() + 1];
			for (int i = 0; i < size + 1; i++) {
				rst[i] = i + this.getKorSuffix();
			}
			return rst;
		}
	};

	int index = 0;
	String korSuffix = "";
	int size = 0;

	private TIME_UNIT(int index, String korSuffix, int size) {
		this.index = index;
		this.korSuffix = korSuffix;
		this.size = size;
	}

	public int getIndex() {return this.index;}

	public String getKorSuffix() {return this.korSuffix;}

	public int getSize() {return this.size;}

	abstract public String[] getUnitList();
}
