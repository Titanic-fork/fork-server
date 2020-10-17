package com.titanic.fork.domain;

import com.titanic.fork.domain.point.SavingPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PointTest {

    @Spy
    private SavingPoint savingPoint;

    @DisplayName("class 이름 가져오는 테스트")
    @Test
    void classNameTest() {
        assertThat(savingPoint.getClass().getSimpleName().split("\\$")[0]).isEqualTo("SavingPoint");
    }

}
