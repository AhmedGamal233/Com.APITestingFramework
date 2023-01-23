Scenario Outline: Verify Create User POST API
Given i create '<user>' user with job '<job>'
When login settings are requested
Then Status code 200 is returned
And Verify following fields
| name | equals    | <user> |
| job  | isNotNull | KAKA land owner  |
| id   | isNotNull | .                |
And Verify following fields in response headers
And check list includes '<user>' with Multi Parameters
| Content-Type | contains | application/json |
Examples:
| user | job           |
| kaka |KAKA land owner|
| 215107,106572,106571,106574,213843 | 115033 |



@tag
Scenario Outline: Scenario with parameter type
Given i set Data table for set parameters
| Parameter1      | parameter1Value      |
| parameter2      | parameter2Value      |
When user <user> parameterType
Then Status code 202 is returned
Examples:
| user                        |addon |
| multiAccountSingleSubActive |      |