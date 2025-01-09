import java.time.LocalTime;

class Solution {
    //
    private final String splitText = ":";
    //
    private final LocalTime startTime = LocalTime.of(0,0,0);
    private LocalTime endTime;
    //
    private LocalTime op_startTime;
    private LocalTime op_endTime;
    //
    private LocalTime now;

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 영상 종료 시간
        this.endTime = this.getTime(video_len.split(this.splitText));
        // 오프닝 시간
        this.op_startTime = this.getTime(op_start.split(this.splitText));
        this.op_endTime = this.getTime(op_end.split(this.splitText));
        // 현재 시간
        this.now = this.getTime(pos.split(this.splitText));
        this.skipOp();

        for(String command : commands) {
            switch (command) {
                case "next":
                    this.moveToNext();
                    break;
                case "prev":
                    this.moveToPrev();
                    break;
            }
            this.skipOp();
        }


        return this.now.isBefore(this.endTime) ? this.getTimeToString(this.now) : this.getTimeToString(this.endTime);
    }

    private LocalTime getTime(String[] times) {
        // String 배열을 LocalTime으로 변환하는 함수
        int min = Integer.parseInt(times[0]);
        int sec = Integer.parseInt(times[1]);
        return LocalTime.of(0, min, sec);
    }

    private void moveToNext() {
        this.now = this.addSecond(this.now);
        if(this.now.isAfter(this.endTime)) {
            this.now = this.endTime;
        }
    }

    private void moveToPrev() {

        if(this.now.getMinute() == 0 && this.now.getSecond() < 10) {
            this.now = LocalTime.of(0, 0, 0);
            return;
        }
        this.now = this.minerSecond(this.now);
    }

    private void skipOp() {
        if(
                this.now.isBefore(this.op_startTime)
                        || this.now.isAfter(this.op_endTime)
        )
            return;

        this.now = this.op_endTime;
    }

    private LocalTime addSecond(LocalTime time) {
        return time.plusSeconds(10);
    }

    private LocalTime minerSecond(LocalTime time) {
        return time.minusSeconds(10);
    }

    private String getTimeToString(LocalTime time) {
        // 답을 표출해내기 위한 함수
        return String.format("%02d:%02d", time.getMinute(), time.getSecond());
    }
}