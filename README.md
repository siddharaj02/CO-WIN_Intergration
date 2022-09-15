# CO-WIN public API Integration and Profile Creator

#CO-WIN public API Integration:

CO-WIN Indian government public APIs to book slot for COVID-19 vaccination.
It helps to download vaccination certificate so that it can be validated during flight booking or entering into any public premises where vaccine certificate is mandatory
for example bank or sports ground.

link for public APIs: https://apisetu.gov.in/api/cowin/cowin-public-v2#/

#Profile builder: 

Profile builder android app using MVVM design pattern with android architecture components i.e Jatpack and written in Kotlin.
Jetpack components used are: ViewModel, Navigation, LiveData, MediatorLiveData etc.

To store json publicly on cloud, jsonbin.io has been used https://jsonbin.io/ instead of Gist.
Documentation of jsonbin.io is available in following link
 https://jsonbin.io/api-reference
 
To check stored json in browser window, here is link available.
 https://api.jsonbin.io/b/5d28930e6e599f247d56d0f7/latest
 
Also used sharedPreferences to store data locally on device which can be available in offline mode
 
Simple validation only on Company details screen and basic UI screens designed. Focus is more on clean architecture.
Added Splash screen and login screen but kept Dashboard screen as launcher activity for now
 
Added UI tests using Espresso framework.
Added Unit tests using Mockito for all ViewModel classes & Repository.
 
Each module is independently testable.
Each module describes project structure with its class names.
 
                                                -------------------
                                                |Activity/Fragments |  (DashboardActivity, CompanyDetailsFragment.... etc)
                                                -------------------
                                                          |
                                                          |   <--interaction via MediatorLiveData only
                                                          |
                                    --------------------------------------------- --------------
                                    |  ViewModel (MediatorLiveData1, MediatorLiveData2,.....)  | (CompanyDetailsViewModel.....etc)
                                    ------------------------------------------------------------
                                                          |
                                                          |
                                                          |  <--interaction via LiveData only
                                                          |
                                     ----------------------------------------------
                                     |              Repository                    | (NetworkRespository)
                                     |                                            |
                                     ----------------------------------------------
                                                          |
                                                          |
                                                          |
                                      |-------------------------------------------|
                          ------------|--------                      -----------------------  
                          |         Model     |  (All pojo classes)  | Network  DataSource   |  (APIServices i.e Retrofit call)
                          |--------------------                      -----------------------
