import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        Stage stage = new Stage(bandage, health, attacks);
        int duration = stage.start();
        for (int time = 1; time <= duration; time++) {
            boolean hasAttack = stage.attack(time);
            if (!hasAttack) {
                stage.heal();
            } else {
                stage.blockSerialSuccess();
            }
            // System.out.println("hasAttack: " + hasAttack + ", status: " + Arrays.toString(stage.getStatus(time)));
        }
        int hp = stage.getHp();
        return hp == 0 ? -1 : hp;
    }
    
    // 스테이지
    class Stage {
        private int start;
        private int duration;
        private int[] bandage;
        private int health;
        private int damage;
        private int serialSuccess;
        private Queue<int[]> attacks;

        public Stage(int[] bandage, int health, int[][] attacks) {
            this.duration = attacks[attacks.length - 1][0];
            this.bandage = bandage;
            this.health = health;
            this.damage = 0;
            this.serialSuccess = 0;
            this.attacks = new LinkedList<>();
            for (int[] attack : attacks) {
                this.attacks.add(attack);
            }
        }

        // 시작 시 경과 예상 시간 반환
        public int start() {
            return this.duration;
        }

        // 공격
        public boolean attack(int time) {
            int[] monster = this.attacks.peek();
            if (monster != null && time == monster[0]) {
                this.damage = Math.min(this.health, this.damage + monster[1]);
                this.attacks.poll();
                return true;
            }
            return false;
        }

        // 연속 성공 차단
        public void blockSerialSuccess() {
            this.serialSuccess = 0;
        }

        // 회복
        public void heal() {
            if (this.damage >= this.health) {
                return;
            }
            this.damage = Math.max(0, this.damage - bandage[1]);
            this.serialSuccess++;
            if (this.serialSuccess == bandage[0]) {
                this.serialSuccess -= bandage[0];
                this.damage = Math.max(0, this.damage - bandage[2]);
            }
        }
        
        public int[] getStatus(int time) {
            int[] status = {time, this.health - this.damage, this.serialSuccess, this.damage};
            return status;
        }

        public int getHp() {
            return this.health - Math.min(this.health, this.damage);
        }
    }
}