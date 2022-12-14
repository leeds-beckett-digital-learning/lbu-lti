/*
 * Copyright 2022 maber01.
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
package uk.ac.leedsbeckett.lti.config;

import java.security.PublicKey;

/**
 *
 * @author maber01
 */
public class KeyConfiguration
{
  final String kid;
  final PublicKey key;
  final boolean enabled;

  public KeyConfiguration( String kid, PublicKey key, boolean enabled )
  {
    this.kid = kid;
    this.key = key;
    this.enabled = enabled;
  }
  
  public String getKid()
  {
    return kid;
  }

  public PublicKey getKey()
  {
    return key;
  }

  public boolean isEnabled()
  {
    return enabled;
  }  
}
