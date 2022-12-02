package com.example.assetjtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AssetjtestApplicationTests {

  @Test
  void contextLoads() {
    var name = "henok";
    assertThat(name).as("check name it start with h").startsWith("h");
  }

  @Test
  void test2() {
    var data= new int[]{1, 2, 3,};
    assertThat(data).hasSize(3).contains(1);

    var integers = List.of(1, 2, 3, 4);
    assertThat(integers).hasSize(4).satisfies(i->i.stream().allMatch(j-> Stream.of(1,2,3,4).anyMatch(h->h==j)));

  }
}
