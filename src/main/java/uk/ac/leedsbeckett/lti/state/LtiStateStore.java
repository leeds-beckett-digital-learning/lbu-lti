/*
 * Copyright 2022 Leeds Beckett University.
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

package uk.ac.leedsbeckett.lti.state;

import java.util.HashMap;
import uk.ac.leedsbeckett.lti.LtiConfiguration;

/**
 *
 * @author jon
 */
public class LtiStateStore
{
  public static final long TIMEOUTSECONDS = 60;
  
  HashMap<String,LtiState> map = new HashMap<>();
  
  protected LtiState newState( LtiConfiguration.Client client )
  {
    return new LtiState( client );    
  }
  
  public LtiState createState( LtiConfiguration.Client client )
  {
    LtiState state = newState( client );
    synchronized ( map )
    {
      map.put( state.id, state );
    }
    return state;
  }
  
  public LtiState getState( String id )
  {
    LtiState state = null;
    synchronized ( map )
    {
      long now = System.currentTimeMillis();
      state = map.get( id );
      if ( state != null && (now - state.timestamp) > (TIMEOUTSECONDS*1000L) )
      {
        map.remove( id );
        state = null;
      }
    }
    return state;
  }
}
  
