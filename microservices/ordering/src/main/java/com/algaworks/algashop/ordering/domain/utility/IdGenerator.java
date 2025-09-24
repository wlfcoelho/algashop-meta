package com.algaworks.algashop.ordering.domain.utility;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;
import io.hypersistence.tsid.TSID;

import java.util.UUID;

public class IdGenerator {

  private static final TimeBasedEpochGenerator timeBasedEpochGenerator
          = Generators.timeBasedEpochGenerator();

  private static final TSID.Factory tsidFactory = TSID.Factory.INSTANCE;

  private IdGenerator() {
  }

  public static UUID generateTimeBasedUUID() {
    return timeBasedEpochGenerator.generate();
  }

  /*
  TSID_NODE
  TSID_NODE_COUNT
  */
  public static TSID generateTimeBasedTSID() {
    return tsidFactory.generate();
  }
}
