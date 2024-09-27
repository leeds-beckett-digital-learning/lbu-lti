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
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maber01
 */
public class Iso8601InstantSerializer extends StdSerializer<Instant>
{
  static final Logger logger = Logger.getLogger(Iso8601InstantSerializer.class.getName() );
  
  public Iso8601InstantSerializer()
  {
    this( null );
  }

  public Iso8601InstantSerializer( Class<Instant> t )
  {
    super( t );
  }

  @Override
  public void serialize( Instant t, JsonGenerator jg, SerializerProvider sp ) throws IOException
  {
    logger.log(Level.INFO, "Serialising Instant {0}", t);
    // toString outputs in ISO8601 format which includes time zone and
    // ought to be comprehensible by multiple derializers.
    jg.writeString( t.toString() );
  }
}
