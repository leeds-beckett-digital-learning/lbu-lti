/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.registration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 *
 * @author jon
 */
public class LtiToolConfigurationCustomParametersSerializer  extends StdSerializer<LtiToolConfigurationCustomParameters>
{
  public LtiToolConfigurationCustomParametersSerializer()
  {
    this(null);
  }

  public LtiToolConfigurationCustomParametersSerializer(Class<LtiToolConfigurationCustomParameters> t)
  {
    super(t);
  }
  
  @Override
  public void serialize( LtiToolConfigurationCustomParameters t, JsonGenerator jg, SerializerProvider sp )
          throws IOException
  {
    jg.writeStartObject();
    for ( String name : t.params.keySet() )
    {
      jg.writeFieldName( name );
      jg.writeString( t.params.get( name ) );
    }
    jg.writeEndObject();
  }
}
