package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @DisplayName("백팀 퀸 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_Queen_q() {
        Queen queen = Queen.createWhite(new Position("a1"));

        assertThat(queen.getSignature()).isEqualTo("q");
    }

    @DisplayName("흑팀 퀸 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_Queen_Q() {
        Queen queen = Queen.createBlack(new Position("a1"));

        assertThat(queen.getSignature()).isEqualTo("Q");
    }

    @DisplayName("타겟 위치가 빈칸일 경우 이동에 성공한다.")
    @Test
    void move_Blank() {
        Queen queen = Queen.createBlack(new Position("c8"));

        assertThat(queen.isMovable(new Blank(new Position("d7"))))
                .isTrue();
        assertThat(queen.isMovable(new Blank(new Position("c7"))))
                .isTrue();
        assertThat(queen.isMovable(new Blank(new Position("d8"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 적 기물일 경우 공격에 성공한다.")
    @Test
    void move_Enemy() {
        Queen queen = Queen.createBlack(new Position("c8"));

        assertThat(queen.isMovable(Queen.createWhite(new Position("d7"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 같은 팀 기물일 경우 이동에 실패한다.")
    @Test
    void move_Ally() {
        Queen queen = Queen.createBlack(new Position("c8"));

        assertThat(queen.isMovable(Queen.createBlack(new Position("d7"))))
                .isFalse();
    }
}
