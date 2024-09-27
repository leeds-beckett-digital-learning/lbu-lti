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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A custom serializer for BigDecimal. Currently just like standard
 * one.
 * 
 * @author maber01
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal>
{
  static final Logger logger = Logger.getLogger(BigDecimalSerializer.class.getName() );
  
  @Override
  public void serialize( BigDecimal t, JsonGenerator jg, SerializerProvider sp ) throws IOException
  {
    logger.log(Level.INFO, "Serialising BigDecimal" );    
    // Output 'canonical' form which preserves data
    // This luckily conforms with JSON float specification:
    // https://www.rfc-editor.org/rfc/rfc8259#section-6
    jg.writeString( t.toString() );
  } 
}
