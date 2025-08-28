package com.algaworks.algashop.ordering.domain.utility;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;

import java.util.UUID;

public class IdGenerator {

  private static final TimeBasedEpochGenerator timeBasedEpochGenerator
          = Generators.timeBasedEpochGenerator();

  private IdGenerator() {
  }

  public static UUID generaTimeBasedUUID() {
    return timeBasedEpochGenerator.generate();
  }
}
