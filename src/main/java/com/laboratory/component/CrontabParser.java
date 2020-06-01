package com.laboratory.component;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.laboratory.constant.StringConst;
import com.laboratory.constant.TIME_UNIT;


@Component
public class CrontabParser {

	public static void main(String[] args) throws Exception {
		CrontabParser cron = new CrontabParser();
		String[] map2 = cron.tmp.split("\n");
		String[] map1 = { "0 22 1-2,5-6,8-10,11-12 * 1-4,6,0 이거실행이거이거" };
		StringBuilder sb = new StringBuilder();
		for (String item : map2) {
			String[] cut = item.split(" ");
			String rst = cut[0] + " " + cut[1] + " " + cut[2] + " " + cut[3] + " " + cut[4] + " " + cut[5];
			String ori = item + "\n";
			String pro = cron.process(rst) + "\n";
			//sb.append("ORI : "+ ori);
			sb.append("PRO "+ pro);
		}
		System.out.println(sb.toString());
	}

	public HashMap<String,String> getKor(String[] params) throws Exception {
		HashMap<String,String> map = new HashMap<String, String>();
		StringBuilder sb = new StringBuilder();
		for(String item : params) {
			sb.append(getKor(item));
		}
		map.put("data",sb.toString());
		return map;
	}
	private String getKor(String param) throws Exception {
		String[] cut = param.split(" ");
		if(cut.length <5 ) {
			new GlobalException().badParamException();
		}
		String rst = cut[0] + " " + cut[1] + " " + cut[2] + " " + cut[3] + " " + cut[4];
		return process(rst);
	}

	private String process(String param) {
		// 0 1 2 3 4 분 시 일 월 요일
		String[] splitArgs = param.split(" ");
		StringBuilder sb = new StringBuilder();
		int size = splitArgs.length;
		for (int i = size; i > 0; i--) {
			sb.append(parse(i - 1, splitArgs[i - 1]));
		}
		//sb.append(splitArgs[5]);		작업내용을 추가로 다섯번쨰 파라미터로 받을거면 위에서 같이 조합 후 이거 어펜드 주석해제
		return sb.toString();

	}

	private String parse(int index, String CHAR) {
		StringBuilder sb = new StringBuilder();
		TIME_UNIT unit = null;
		switch (index) {
		case 4: // 요일
			unit = TIME_UNIT.DAY;
			sb.append(builder(unit, CHAR));
			break;
		case 3: // 월
			unit = TIME_UNIT.MONTH;
			sb.append(builder(unit, CHAR));
			break;
		case 2: // 일
			unit = TIME_UNIT.DATE;
			sb.append(builder(unit, CHAR));
			break;
		case 1: // 시
			unit = TIME_UNIT.HOUR;
			sb.append(builder(unit, CHAR));
			break;
		case 0: // 분
			unit = TIME_UNIT.MIN;
			sb.append(builder(unit, CHAR));
			sb.append(StringConst.TIMES_SUFFIX);
			break;
		default:

			break;
		}
		return sb.toString();
	}

	private boolean isWildCard(String CHAR) {
		return CHAR.contains("*");
	}

	// EX> 10-12 시, 10시부터 12시까지 (매분마다, 매시마다, 15분45분에)
	private String fromAtoB(String[] unitList, String param) {
		int hyphenIndex = param.indexOf("-");
		String FROM = param.substring(0, hyphenIndex);
		int intFROM = Integer.valueOf(FROM);
		String korFROM = unitList[intFROM];

		String TO = param.substring(hyphenIndex + 1,param.length());
		int intTO = Integer.valueOf(TO);
		String korTO = unitList[intTO];
		return korFROM + " 부터 " + korTO + " 까지 ";
	}

	private String builder(TIME_UNIT unit, String param) {
		String[] unitList = unit.getUnitList();
		String suffix = unit.getKorSuffix();

		StringBuilder sb = new StringBuilder();
		if (isWildCard(param)) { // 모든 일자를 포함하는 *
			if(unit.equals(TIME_UNIT.DAY)) {		//매요일이 어색해서 얘만 따로뺌
				sb.append(StringConst.EVERY_DAY);
			}
			else {
				sb.append(StringConst.EVERY);
			}
			if (param.contains("/")) { // 특별한 주기를 표현하는 * (TODO 짝수일 표현법 추가해야함)
				int index = param.indexOf("/");
				String pattern = param.substring(index + 1, param.length()); // 주기를 뽑아내서 n주기로를 붙인다
				sb.append(pattern);
			}
			sb.append(suffix);
		} else if (param.contains(",")) { // 특정일자를 표현하는 ,
			String[] list = param.split(","); // EX> 1,2,5-6,7 크게 콤마로 짜르고 하나의 아이템을 처리
			for (int i = 0; i < list.length; i++) {
				String item = list[i];
				if (i != 0)
					sb.append(StringConst.AND);
				if (item.contains("-")) { // 각 아이템에 특정기간이 포함되어 있나?
					sb.append(fromAtoB(unitList, item));
				} else {
					int val = Integer.valueOf(item);
					sb.append(unitList[val]);
				}

			}
		} else if (param.contains("-")) {
			sb.append(fromAtoB(unitList, param));
		} else {

			int val = Integer.valueOf(param);
			sb.append(unitList[val]);
		}
		return sb.toString();
	}

	 String tmp =
			 "*/15 17-18 * * 1-5 java -jar /home/batch/app/approvalBatch/localpay-batch-bsRcvTxReconcJob.jar bsRcvTxReconcJob PKT03212\r\n"
			+ "*/5 17-18 * * 1-5 java -jar /home/batch/app/approvalBatch/localpay-batch-bsRcvTxReconcJob.jar bsRcvTxReconcJob PKT03222\r\n"
			+ "50 4 * * * crontab -l > /home/batch/app/approvalBatch/crontab_bak.txt\r\n"
			+ "* * * * * java -jar /home/batch/app/approvalBatch/localpay-batch-publishCancel.jar publishCancelJob\r\n"
			+ "0 2 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-userExpire.jar userExpireJob\r\n"
			+ "0 1 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-presentAutoReject.jar presentAutoRejectJob\r\n"
			+ "30 0 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-bcComparison.jar bcComparisonJob beforeday\r\n"
			+ "30 5 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-budgetComparison.jar budgetComparisonJob beforeday\r\n"
			+ "0 3 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsInfoCollect.jar statisticsInfoCollectJob itemModifyInfoCollectJob\r\n"
			+ "30 3 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsInfoCollect.jar statisticsInfoCollectJob payLogCollectJob\r\n"
			+ "0 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsInfoCollect.jar statisticsInfoCollectJob refundInfoCollectJob\r\n"
			+ "30 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsInfoCollect.jar statisticsInfoCollectJob refundInfoCompleteCollectJob\r\n"
			+ "0 5 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsInfoCollect.jar statisticsInfoCollectJob itemModifyInfoAcceptCollectJob\r\n"
			+ "30 5 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsInfoCollect.jar statisticsInfoCollectJob refundInfoCancelCollectJob\r\n"
			+ "0 6 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsInfoCollect.jar statisticsInfoCollectJob companyInfoCollectJob\r\n"
			+ "30 2 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsSum.jar statisticsSumJob moneyStatisticsJob\r\n"
			+ "0 2 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsBalanceCollect.jar statisticsBalanceCollectJob\r\n"
			+ "0 2 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsDepoWithSum.jar statisticsDepoWithSumJob\r\n"
			+ "0 2 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsInsideSwitchSum.jar statisticsInsideSwitchSumJob\r\n"
			+ "0 2 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-blockChainAmbassador.jar blockChainAmbassadorJob\r\n"
			+ "0 2 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-settleAmbassador.jar settleAmbassadorJob\r\n"
			+ "0 7 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsJoinSum.jar joinStatisticsJob\r\n"
			+ "0 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-cardPublish.jar cardPublishJob\r\n"
			+ "3 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-cardCompare.jar cardCompareJob\r\n"
			+ "6 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-cardPuchaseCancel.jar cardPuchaseCancelJob\r\n"
			+ "30 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-cardAmbassador.jar cardAmbassadorJob\r\n"
			+ "0 22 * * 5 /home/batch/app/approvalBatch/cardFileBackup.sh\r\n"
			+ "55 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-cardBalance.jar cardBalanceJob\r\n"
			+ "30 6 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-hanacardBalance.jar hanacardBalanceJob\r\n"
			+ "0 14 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-hanacardSettle.jar hanacardSettleJob\r\n"
			+ "0 12 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-settleCashReceiptComparison.jar settleCashReceiptComparisonJob\r\n"
			+ "0 23 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-settleCashReceipt.jar settleCashReceiptJob\r\n"
			+ "30 7 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsJoinSubStoreJob.jar statisticsJoinSubStoreJob\r\n"
			+ "30 1 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsCurrencyIssue.jar statisticsCurrencyIssueJob\r\n"
			+ "20 6 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-dashboardJob.jar dashboardJob\r\n"
			+ "0 9 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-autoCharge.jar autoChargeJob\r\n"
			+ "10 8 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsCardUsage.jar statisticsCardUsageJob\r\n"
			+ "20 8 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsLocalCurrencyUse.jar statisticsLocalCurrencyUseJob\r\n"
			+ "40 3 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-statisticsRewardCashJob.jar statisticsRewardCashJob\r\n"
			+ "55 2 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-hanacardStoreJob.jar hanacardStoreJob\r\n"
			+ "0 5 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-dormancyProcessingJob.jar dormancyProcessingJob\r\n"
			+ "10,30,50 * * * * /home/BCGatorCli/bcgator.sh\r\n"
			+ "0 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-bcCardPublishCollectDataProcess.jar bcCardPublishCollectDataProcessJob\r\n"
			+ "30 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-bcCardCompareCollectDataProcess.jar bcCardCompareCollectDataProcessJob\r\n"
			+ "6 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-bcCardPurchaseCancelCollectDataProcess.jar bcCardPurchaseCancelCollectDataProcessJob\r\n"
			+ "55 4 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-bcCardSettleDataProcess.jar bcCardSettleDataProcessJob\r\n"
			+ "30 6 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-bcCardSettleCollect.jar bcCardSettleCollectJob\r\n"
			+ "55 2 * * * java -jar /home/batch/app/approvalBatch/localpay-batch-bcCardStoreCollect.jar bcCardStoreCollectJob\r\n"
			+ "0 22 * * 5 /home/batch/app/approvalBatch/bcCardFileBackup.sh" + "0 22 1-2,5-6,8-10,11-12 * 5 이거실행 이거이거";
}
