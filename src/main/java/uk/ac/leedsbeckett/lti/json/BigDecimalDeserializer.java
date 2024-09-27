/*
 * Copyright 2024 maber01.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leedsbeckett.lti.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import static uk.ac.leedsbeckett.lti.json.Iso8601InstantSerializer.logger;

/**
 * At present is unmodified standard deserializer from Jackson databind
 * Leaves open possibility of later customisation.
 * 
 * @author maber01
 */
public class BigDecimalDeserializer extends NumberDeserializers.BigDecimalDeserializer
{
  static final Logger logger = Logger.getLogger(BigDecimalDeserializer.class.getName() );
  

  @Override
  public BigDecimal deserialize( JsonParser p, DeserializationContext ctxt ) throws IOException
  {
    logger.log(Level.INFO, "Deserialising BigDecimal" );    
    return super.deserialize( p, ctxt );
  }
  
}
