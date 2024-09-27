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

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maber01
 */
public class Iso8601InstantDeserializer extends StdDeserializer<Instant>
{
  static final Logger logger = Logger.getLogger(Iso8601InstantDeserializer.class.getName() );

  public Iso8601InstantDeserializer()
  {
    this(null);
  }
  
  public Iso8601InstantDeserializer( Class<?> vc )
  {
    super( vc );
  }

  @Override
  public Instant deserialize( JsonParser jp, DeserializationContext dc ) throws IOException, JacksonException
  {
    logger.log(Level.INFO, "Deserialising Instant");
    switch ( jp.currentTokenId() )
    {
      case JsonTokenId.ID_STRING:    
        return Instant.parse( jp.getText() );
      case JsonTokenId.ID_NULL:
        return null;
    }
    return (Instant) dc.handleUnexpectedToken( getValueType( dc ), jp );    
  }
}
