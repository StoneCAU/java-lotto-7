package lotto.utils;

import static java.lang.String.format;
import static lotto.constant.WinningPrize.SECOND_PRIZE;

import java.util.List;
import lotto.dto.WinningStat;

public class WinningSummaryGenerator {
    private final static String NORMAL_STAT = "%d개 일치 (%s)원 - %d개";
    private final static String BONUS_STAT = "%d개 일치, 보너스 볼 일치 (%s)원 - %d개";

    public static List<String> generate(List<WinningStat> winningStats) {
        return winningStats.stream()
                .map(WinningSummaryGenerator::convertToString)
                .toList();
    }

    private static String convertToString(WinningStat winningStat) {
        String price = PriceFormatter.formatToKoreanStyle(winningStat.prizeAmount());

        if (isSecondPrize(winningStat)) {
            return format(BONUS_STAT, winningStat.matchingCount(), price, winningStat.prizeCount());
        }

        return format(NORMAL_STAT, winningStat.matchingCount(), price, winningStat.prizeCount());
    }

    private static boolean isSecondPrize(WinningStat winningStat) {
        return winningStat.matchingCount() == SECOND_PRIZE.getMatchingCount() &&
                winningStat.isBonusMatch();
    }
}