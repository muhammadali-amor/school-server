package ali.school_server;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
//        long milliseconds = 1486815313230L;
//        LocalDateTime cvDate =
//                Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
//        System.out.println(cvDate);
//        LocalDateTime utcDate =
//                Instant.ofEpochMilli(milliseconds).atZone(ZoneId.of("UTC")).toLocalDateTime();
//        System.out.println(utcDate);

        LocalDateTime localUTCDT = LocalDateTime.of(2024, 10, 19, 9, 50, 55);
        long localUTDTInMilli = localUTCDT.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();

        LocalDateTime utcDate =
                Instant.ofEpochMilli(localUTDTInMilli).atZone(ZoneId.of("UTC")).toLocalDateTime();

        LocalDateTime localUTCDT2 = LocalDateTime.of(2024, 10, 19, 9, 50, 55);
        long localUTDTInMilli2 = localUTCDT2.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();

        LocalDateTime utcDate2 =
                Instant.ofEpochMilli(localUTDTInMilli2).atZone(ZoneId.of("UTC")).toLocalDateTime();

        System.out.println(utcDate.equals(utcDate2));
//        System.out.println(localUTDTInMilli);
    }
}
