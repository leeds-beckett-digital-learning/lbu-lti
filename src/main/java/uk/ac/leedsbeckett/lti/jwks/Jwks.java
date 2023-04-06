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
package uk.ac.leedsbeckett.lti.jwks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A JSON Web Key Set.
 * 
 * @author maber01
 */
public class Jwks implements Serializable
{
  private final ArrayList<Jwk> keys;
  private final HashMap<String,Jwk> keymap = new HashMap<>();

  public Jwks( @JsonProperty("keys") ArrayList<Jwk> keys )
  {
    this.keys = keys;
    for ( Jwk jwk : keys )
      keymap.put( jwk.getKid(), jwk );
  }

  public List<Jwk> getKeys()
  {
    return keys;
  }

  @JsonIgnore
  public Jwk getKey( String kid )
  {
    return keymap.get( kid );
  }
}
