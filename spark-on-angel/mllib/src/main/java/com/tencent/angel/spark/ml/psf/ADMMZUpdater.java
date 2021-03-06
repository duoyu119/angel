/*
 * Tencent is pleased to support the open source community by making Angel available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in 
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/Apache-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package com.tencent.angel.spark.ml.psf;

import com.tencent.angel.ml.matrix.psf.update.enhance.map.MapFunc;
import io.netty.buffer.ByteBuf;

public class ADMMZUpdater implements MapFunc {
  private double kappa;
  public ADMMZUpdater(double kappa) {
    this.kappa = kappa;
  }

  public ADMMZUpdater() {
    super();
  }

  @Override
  public double call(double x) {
    return Math.max(0, x - kappa) - Math.max(0, -x - kappa);
  }

  @Override
  public void serialize(ByteBuf buf) {
    buf.writeDouble(kappa);
  }

  @Override
  public void deserialize(ByteBuf buf) {
    kappa = buf.readDouble();
  }

  @Override
  public int bufferLen() {
    return 8;
  }

}
