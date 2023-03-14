/*
 * Copyright 2023 maber01.
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
package uk.ac.leedsbeckett.lti.services.nrps.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maber01
 */
public class NrpsMembershipContainer implements Serializable
{
  static final Logger logger = Logger.getLogger( NrpsMembershipContainer.class.getName() );
  
  private final String id;
  private final NrpsContext context;
  private final List<NrpsMember> members;

  public NrpsMembershipContainer( 
          @JsonProperty("id")       String id, 
          @JsonProperty("context")  NrpsContext context, 
          @JsonProperty("members")  List<NrpsMember> members )
  {
    this.id = id;
    this.context = context;
    this.members = members;
  }
  
  public String getId()
  {
    return id;
  }
  
  public List<NrpsMember> getMembers()
  {
    return members;
  }
  
  public void dumpToLog()
  {
    logger.log( Level.INFO, id );
    context.dumpToLog();
    for ( NrpsMember m : members )
      m.dumpToLog();
  }
}
