import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        KojectHelper_iosKt.startKoject()
    }
    
	var body: some Scene {
		WindowGroup {
            TodoListPage()
		}
	}
}
