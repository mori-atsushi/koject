import XCTest

final class IosUITests: XCTestCase {
    let app = XCUIApplication()
    
    func testLaunch() {
        app.launchArguments = ["TESTING"]
        app.launch()
    }
}
