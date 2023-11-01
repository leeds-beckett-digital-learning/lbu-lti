/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.registration;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author jon
 */
public class LtiToolConfigurationCustomParametersDeserializer  extends StdDeserializer<LtiToolConfigurationCustomParameters>
{
  public LtiToolConfigurationCustomParametersDeserializer()
  {
    this(null);
  }

  public LtiToolConfigurationCustomParametersDeserializer(Class<LtiToolConfigurationCustomParameters> t)
  {
    super(t);
  }

  @Override
  public LtiToolConfigurationCustomParameters deserialize( JsonParser jp, DeserializationContext dc )
          throws IOException, JacksonException
  {
    LtiToolConfigurationCustomParameters p = new LtiToolConfigurationCustomParameters();
    
    JsonNode node = jp.getCodec().readTree(jp);
    Iterator<String> it = node.fieldNames();
    while ( it.hasNext() )
    {
      String name = it.next();
      p.params.put( name, node.findValue( name ).asText() );
    }
    return p;
  }
  
}
